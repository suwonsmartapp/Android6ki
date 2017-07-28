package com.example.myapplication.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.myapplication.R;

/**
 * Created by junsuk on 2017. 7. 28..
 */

public class DialogUtil {
    public static AlertDialog createAlertDialog(Context context,
                                                DialogInterface.OnClickListener listener) {
        // 물어보자 AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("삭제");
        builder.setMessage("정말로 삭제하시겠습니까?");
        // 바깥 부분 클릭 했을 때 닫기
        builder.setCancelable(false);
        builder.setIcon(R.drawable.girl);
        builder.setPositiveButton("예", listener);
        builder.setNegativeButton("아니오", listener);

        return builder.create();
    }
}
