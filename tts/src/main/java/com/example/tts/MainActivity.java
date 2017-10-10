package com.example.tts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 1000;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
//                    mTts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null);

                    promptSpeechInput();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 메모리 해제
        if (mTts != null) {
            mTts.shutdown();
        }
    }

    /**
     * 음성인식 인텐트 수행
     */
    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "아무 말이나 해 주세요");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String answer = result.get(0);

                    handleMessage(answer);


                    mTts.speak(answer, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            }

        }
    }

    private void handleMessage(String answer) {
        if (answer.contains(getString(R.string.command_finish))) {
            finish();
        }
    }

    public void startVoiceCommand(View view) {
        promptSpeechInput();
    }
}
