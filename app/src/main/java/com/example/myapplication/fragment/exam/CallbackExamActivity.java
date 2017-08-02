package com.example.myapplication.fragment.exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;

public class CallbackExamActivity extends AppCompatActivity implements ChatFragment.OnSendMessageListener {

    private ChatFragment mChatFragment1;
    private ChatFragment mChatFragment2;
    private ChatFragment mChatFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback_exam);

        mChatFragment1 = (ChatFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_chat_1);
        mChatFragment2 = (ChatFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_chat_2);
        mChatFragment3 = (ChatFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_chat_3);
    }

    @Override
    public void onSendMessage(String message) {
        mChatFragment1.sendMessage(message);
        mChatFragment2.sendMessage(message);
        mChatFragment3.sendMessage(message);
    }
}
