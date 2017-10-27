package com.example.multichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.multichat.recycler.MessageRecyclerAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.message_edit_text)
    EditText mMessageEditText;
    @BindView(R.id.message_recycler_view)
    RecyclerView mMessageRecyclerView;

    MessageRecyclerAdapter mAdapter;

    private Gson mGson = new Gson();

    private ChatClient mChatClient;

    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        mMessageRecyclerView.setLayoutManager(manager);

        mAdapter = new MessageRecyclerAdapter();
        mMessageRecyclerView.setAdapter(mAdapter);

        mChatClient = new ChatClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mChatClient.connect();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatClient.disconnect();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.code) {
            case MessageEvent.CODE_ERROR:
                handleError(event.message);
                break;
            case MessageEvent.CODE_GREETING:
                Toast.makeText(this, event.message + " 님이 입장하셨습니다", Toast.LENGTH_SHORT).show();
                break;
            case MessageEvent.CODE_MESSAGE:
                handleMessage(event.message);
                break;
        }
    }

    private void handleMessage(String json) {
        Log.d(TAG, "handleMessage: " + json);

        mStringBuilder.append(json).append("\n");

        // json 을 객체로
        MsgInfo msgInfo = mGson.fromJson(json, MsgInfo.class);
        if (msgInfo.getNickName().equals(mChatClient.mName)) {
            msgInfo.setItemType(MessageRecyclerAdapter.TYPE_ME);
        } else {
            msgInfo.setItemType(MessageRecyclerAdapter.TYPE_YOU);
        }
        mAdapter.addItem(msgInfo);
        mMessageRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
    }

    private void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.send_button)
    public void onViewClicked() {
        mChatClient.sendMessage(mMessageEditText.getText().toString());
        mMessageEditText.setText("");
    }
}
