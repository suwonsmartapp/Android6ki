package com.example.myapplication.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.myapplication.R;

/**
 * Created by junsuk on 2017. 8. 3..
 */

public class MyAlertDialogFragment extends DialogFragment {
    private static final String TAG = MyAlertDialogFragment.class.getSimpleName();

    private DialogInterface.OnClickListener mListener;

    public static MyAlertDialogFragment newInstance(DialogInterface.OnClickListener listener) {
        Bundle args = new Bundle();

        MyAlertDialogFragment fragment = new MyAlertDialogFragment();
        fragment.setArguments(args);
        fragment.setPositiveButtonClickListener(listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("삭제");
        builder.setMessage("정말로 삭제하시겠습니까?");
        // 바깥 부분 클릭 했을 때 닫기
        builder.setCancelable(false);
        builder.setIcon(R.drawable.girl);
        builder.setPositiveButton("예", mListener);
        builder.setNegativeButton("아니오", null);
        return builder.create();
    }

    public void setPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        Log.d(TAG, "setPositiveButtonClickListener: ");
        mListener = listener;
    }
}
