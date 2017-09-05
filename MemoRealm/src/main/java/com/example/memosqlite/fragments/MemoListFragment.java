package com.example.memosqlite.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memorealm.R;
import com.example.memosqlite.activity.DetailActivity;
import com.example.memosqlite.adapters.MemoRecyclerAdapter;
import com.example.memosqlite.models.Memo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoListFragment extends Fragment implements MemoRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private Realm mRealm;
    private MemoRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();

        // 아래 밑줄
        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {
        // 리사이클러뷰 레이아웃 매니저
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Realm 에서 데이터 읽어오기
        RealmResults<Memo> data = mRealm.where(Memo.class).findAllSorted("id", Sort.DESCENDING);

        // 어댑터에 데이터 설정
        mAdapter = new MemoRecyclerAdapter(data);

        // 클릭 이벤트
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mRealm.close();
    }

    @Override
    public void onItemClicked(Memo memo) {
        // 수정 화면으로 이동
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("id", memo.getId());
        startActivity(intent);
    }
}
