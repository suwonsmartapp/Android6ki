package com.example.multichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.result_text_view)
    TextView mResultTextView;

    private ChatClient mChatClient;

    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    private void handleMessage(String message) {
        Log.d(TAG, "handleMessage: " + message);

        mStringBuilder.append(message).append("\n");
        mResultTextView.setText(mStringBuilder.toString());
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
