package com.suwonsmartapp.warikang;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WarikanFragment extends Fragment implements WarikanContract.View, View.OnClickListener {

    private static final String TAG = WarikanFragment.class.getSimpleName();
    private WarikanContract.UserActionListener mPresenter;

    public WarikanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warikan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        view.findViewById(R.id.camera_layout).setOnClickListener(this);
        view.findViewById(R.id.create_button).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mPresenter = new WarikanPresenter(this);
    }

    @Override
    public void openCamera(String path) {
        Log.d(TAG, "openCamera: ");
    }

    @Override
    public void showImagePreview(@NonNull String uri) {
        Log.d(TAG, "showImagePreview: ");
    }

    @Override
    public void showImageError() {
        Log.d(TAG, "showImageError: ");
    }

    @Override
    public void shareResult(String result, String image) {
        Log.d(TAG, "shareResult: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_layout:
                mPresenter.takePicture();
                break;
            case R.id.create_button:
                mPresenter.calculate(0, 0, 0, null, "코멘트");
                break;
        }
    }
}
