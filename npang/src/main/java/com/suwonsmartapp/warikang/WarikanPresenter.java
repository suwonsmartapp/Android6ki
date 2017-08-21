package com.suwonsmartapp.warikang;

import android.net.Uri;
import android.util.Log;

/**
 * Created by junsuk on 2017. 8. 21..
 */

public class WarikanPresenter implements WarikanContract.UserActionListener {
    public static final String TAG = WarikanPresenter.class.getSimpleName();

    private WarikanContract.View mView;

    public WarikanPresenter(WarikanContract.View view) {
        this.mView = view;
    }

    @Override
    public void takePicture() {
        Log.d(TAG, "takePicture: ");
        // 기타 로직
        mView.openCamera("");

        boolean isImageAvailable = true;

        if (isImageAvailable) {
            imageAvailable();
        } else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageAvailable() {
        Log.d(TAG, "imageAvailable: ");
        // 잘 찍혔다
        mView.showImagePreview("경로");
    }

    @Override
    public void imageCaptureFailed() {
        Log.d(TAG, "imageCaptureFailed: ");
        // 잘 안 찍혔다
        mView.showImageError();
    }

    @Override
    public void calculate(int totalPrice, int memberCount, int pricePerOne, Uri image, String comment) {
        Log.d(TAG, "calculate: ");
        // Model 에서 계산 (Calculator)
        mView.shareResult("", "");
    }
}
