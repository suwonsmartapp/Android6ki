package com.example.flash;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

public class TorchFactory {

    public static Torch createTorch(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new MarshmallowTorch(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return new LollipopTorch(context);
        } else {
            Toast.makeText(context, "이 기기는 지원 안 됩니다", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
