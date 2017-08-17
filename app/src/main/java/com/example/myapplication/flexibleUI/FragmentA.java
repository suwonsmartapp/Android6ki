package com.example.myapplication.flexibleUI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by junsuk on 2017. 8. 17..
 */

public class FragmentA extends ListFragment {

    private OnHeadlineClickListener mListener;

    public interface OnHeadlineClickListener {
        void onHeadlineClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnHeadlineClickListener) {
            mListener = (OnHeadlineClickListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                Articles.Headlines);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (mListener != null) {
            mListener.onHeadlineClicked(position);
        }
    }
}
