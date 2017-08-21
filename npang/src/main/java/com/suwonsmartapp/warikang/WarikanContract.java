package com.suwonsmartapp.warikang;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by junsuk on 2017. 8. 18..
 */

public interface WarikanContract {

    interface View {
        void openCamera(String path);

        void showImagePreview(@NonNull String uri);

        void showImageError();

        void shareResult(String result, String image);
    }

    interface UserActionListener {
        void takePicture();

        void imageAvailable();

        void imageCaptureFailed();

        void calculate(int totalPrice, int memberCount, int pricePerOne, Uri image, String comment);
    }
}
