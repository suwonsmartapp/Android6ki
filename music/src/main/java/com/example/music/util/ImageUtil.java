package com.example.music.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;

/**
 * Created by junsuk on 2017. 9. 14..
 */

public class ImageUtil {
    public static void changeVectorDrawable(Context context, ImageView view, int resId) {
        Drawable drawable = ActivityCompat.getDrawable(context, resId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
            view.setImageDrawable(vectorDrawable);
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            view.setImageDrawable(bitmapDrawable);
        }
    }
}
