package com.example.flash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Torch torch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        torch = TorchFactory.createTorch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        torch.flashOn();
    }

    @Override
    protected void onPause() {
        super.onPause();

        torch.flashOff();
    }
}
