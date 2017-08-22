package com.suwonsmartapp.warikang;

import android.util.Log;

import com.suwonsmartapp.warikang.models.Warikan;

/**
 * Created by junsuk on 2017. 8. 21..
 */

public class WarikanPresenter implements WarikanContract.UserActionListener {
    public static final String TAG = WarikanPresenter.class.getSimpleName();

    private WarikanContract.View mView;
    private Warikan mModel;

    public WarikanPresenter(WarikanContract.View view) {
        mModel = new Warikan();
        mView = view;
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
    public void calculate(int totalPrice, int memberCount) {
        Log.d(TAG, "calculate: ");
        // Model 에서 계산 (Calculator)

        mModel.setTotalPrice(totalPrice);
        mModel.setPeopleCount(memberCount);

        mView.showResult(mModel.calculate());

//        StringBuilder builder = new StringBuilder();
//        builder.append("총 금액 : ").append(totalPrice)
//                .append("\n인원 수 : ").append(memberCount)
//                .append("\n=============")
//                .append("\n낼 금액 ").append(mModel.calculate());
//
//        mView.shareResult(builder.toString(), "");
    }
}
