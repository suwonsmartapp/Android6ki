package com.suwonsmartapp.warikang;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WarikanFragment extends Fragment implements WarikanContract.View, View.OnClickListener, TextWatcher {

    private static final String TAG = WarikanFragment.class.getSimpleName();
    private WarikanContract.UserActionListener mPresenter;
    private EditText mTotalPriceEditText;
    private EditText mPeopleCountEditText;
    private EditText mCommentEditText;
    private EditText mPriceEditText;

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

        mTotalPriceEditText = view.findViewById(R.id.total_price_edit);
        mPeopleCountEditText = view.findViewById(R.id.people_count_edit);
        mPriceEditText = view.findViewById(R.id.price_edit);
        mCommentEditText = view.findViewById(R.id.comment_edit);

        mTotalPriceEditText.addTextChangedListener(this);
        mPeopleCountEditText.addTextChangedListener(this);
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
    public void showResult(int price) {
        mPriceEditText.setText("" + price);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_layout:
                mPresenter.takePicture();
                break;
            case R.id.create_button:
                String totalPrice = mTotalPriceEditText.getText().toString();
                String peopleCount = mPeopleCountEditText.getText().toString();
                String price = mPriceEditText.getText().toString();
                String comment = mCommentEditText.getText().toString();

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        try {
            int totalPrice = strToInt(mTotalPriceEditText);
            int peopleCount = strToInt(mPeopleCountEditText);

            mPresenter.calculate(totalPrice, peopleCount);
        } catch (ArithmeticException e) {
            Toast.makeText(getActivity(), "0 으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private int strToInt(TextView view) {
        try {
            return Integer.parseInt(view.getText().toString());
        } catch (NumberFormatException e) {
            // "" 일 경우 파싱 에러
            return 1;
        }

    }
}
