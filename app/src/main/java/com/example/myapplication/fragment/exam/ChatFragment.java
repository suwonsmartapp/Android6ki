package com.example.myapplication.fragment.exam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class ChatFragment extends Fragment {
    private OnSendMessageListener mListener;
    private EditText mMessageEditText;
    private TextView mTextView;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMessageEditText = (EditText) view.findViewById(R.id.message_edit);
        mTextView = (TextView) view.findViewById(R.id.text_text);

        view.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSendMessage(mMessageEditText.getText().toString());
                mMessageEditText.setText("");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendMessageListener) {
            mListener = (OnSendMessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSendMessageListener {
        void onSendMessage(String message);
    }

    public void sendMessage(String message) {
        mTextView.setText(message + "\n" + mTextView.getText().toString());
    }
}
