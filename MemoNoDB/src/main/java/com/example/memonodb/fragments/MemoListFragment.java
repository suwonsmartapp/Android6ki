package com.example.memonodb.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.memonodb.activity.DetailActivity;
import com.example.memonodb.activity.MainActivity;
import com.example.memonodb.adapters.MemoAdapter;
import com.example.memonodb.models.Memo;

import java.util.ArrayList;
import java.util.List;

import static com.example.memonodb.activity.MainActivity.REQUEST_CODE_MEMO_UPDATE;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoListFragment extends ListFragment {

    private MemoAdapter mAdapter;
    private List<Memo> mMemoList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMemoList = new ArrayList<>();
        mMemoList.add(new Memo("제목 1", "메모 1"));
        mMemoList.add(new Memo("제목 2", "메모 2"));
        mMemoList.add(new Memo("제목 3", "메모 3"));

        // 뒤집기
//        Collections.reverse(mMemoList);

        mAdapter = new MemoAdapter(mMemoList);

        setListAdapter(mAdapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("삭제하시겠습니까");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mMemoList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Memo memo = mMemoList.get(position);
        memo.setId(position);

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(MainActivity.KEY_DATA, memo);
        getActivity().startActivityForResult(intent, REQUEST_CODE_MEMO_UPDATE);
    }

    public void addMemo(Memo memo) {
        mMemoList.add(memo);
        mAdapter.notifyDataSetChanged();
    }

    public void updateMemo(Memo memo) {
        mMemoList.set((int) memo.getId(), memo);
        mAdapter.notifyDataSetChanged();
    }
}
