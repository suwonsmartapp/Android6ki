package com.example.multichat;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private final static String SERVER_HOST = "suwonsmartapp.iptime.org";
    private final static int SERVER_PORT = 5000;
    private final static String NICKNAME = "오준석";

    private Socket mSocket;
    public String mName;
    private ClientWrite mClientWrite;

    public static void main(String[] args) {
        new ChatClient().connect();
    }

    public void disconnect() {
        try {
            if (mSocket != null) {
                mSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        mClientWrite.sendMessage(message);
    }

    public void connect() {
        try {
            mSocket = new Socket(SERVER_HOST, SERVER_PORT);
            mClientWrite = new ClientWrite(NICKNAME);
            ClientRead clientRead = new ClientRead();
            clientRead.start();
        } catch (UnknownHostException e) {
//            e.printStackTrace();
            MessageEvent event = new MessageEvent(MessageEvent.CODE_ERROR, e.getMessage());
            EventBus.getDefault().post(event);
        } catch (IOException e) {
//            e.printStackTrace();
            MessageEvent event = new MessageEvent(MessageEvent.CODE_ERROR, e.getMessage());
            EventBus.getDefault().post(event);
        }
    }

    class ClientRead extends Thread {
        private DataInputStream mInputStream;

        @Override
        public void run() {
            try {
                mInputStream = new DataInputStream(mSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.run();
            try {
                // 계속 듣기만
                while (mInputStream != null) {
                    // json 파싱
                    String json = mInputStream.readUTF();
                    try {
                        MsgInfo msgInfo = new Gson().fromJson(json, MsgInfo.class);
//                        System.out.println(json);
                        MessageEvent event = new MessageEvent(MessageEvent.CODE_MESSAGE, json);
                        EventBus.getDefault().post(event);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 접속 종료시
                mSocket = null;
            }
        }
    }



    class ClientWrite {

        private DataOutputStream mOutputStream;

        public void sendMessage(final String message) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long time = System.currentTimeMillis();
                        MsgInfo msgInfo = new MsgInfo(mName, message, time);
                        Gson gson = new Gson();
                        mOutputStream.writeUTF(gson.toJson(msgInfo));
                        mOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        MessageEvent event = new MessageEvent(MessageEvent.CODE_ERROR, e.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }).start();
        }

        public ClientWrite(String nickName) {
            try {
                mName = nickName;
                mOutputStream = new DataOutputStream(mSocket.getOutputStream());
                mOutputStream.writeUTF(nickName);
                mOutputStream.flush();
//                System.out.println("id : " + nickName + "접속 완료");
                MessageEvent event = new MessageEvent(MessageEvent.CODE_GREETING, nickName);
                EventBus.getDefault().post(event);
            } catch (IOException e) {
                e.printStackTrace();
//                System.out.println("writeUTF IOException");

                MessageEvent event = new MessageEvent(MessageEvent.CODE_ERROR, "writeUTF IOException");
                EventBus.getDefault().post(event);
            }

            long time = System.currentTimeMillis();
            MsgInfo msgInfo = new MsgInfo(mName, "안녕하세요", time);

            Gson gson = new Gson();
//					String json = "{\"nickName\":\"" + nickName + "\",\"msg\":\"" + msg + "\",\"time\":\"" + time + "\"}";
            try {
                mOutputStream.writeUTF(gson.toJson(msgInfo));
//                    System.out.println(gson.toJson(msgInfo));
                mOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
