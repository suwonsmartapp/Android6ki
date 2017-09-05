package com.example.memosqlite.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.memorealm.R;
import com.example.memosqlite.models.Memo;

import java.util.Stack;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by junsuk on 2017. 9. 4..
 */

public class MemoRecyclerAdapter extends RealmRecyclerViewAdapter<Memo, MemoRecyclerAdapter.ViewHolder> {

    private Stack<Memo> mUndoStack;

    public void delete(Memo memo) {
        Realm realm = Realm.getDefaultInstance();
        mUndoStack.push(realm.copyFromRealm(memo));
        realm.beginTransaction();

        memo.deleteFromRealm();

        realm.commitTransaction();
        realm.close();
    }

    public void undo() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Memo memo = mUndoStack.pop();
        realm.insert(memo);

        realm.commitTransaction();
        realm.close();
    }

    public interface OnItemClickListener {
        void onItemClicked(Memo memo);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MemoRecyclerAdapter(@Nullable OrderedRealmCollection<Memo> data) {
        super(data, true);
        mUndoStack = new Stack<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(getItem(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo memo = getItem(position);
        holder.titleTextView.setText(memo.getTitle());
        holder.memoTextView.setText(memo.getMemo());
        holder.memo = memo;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView memoTextView;
        public Memo memo;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text1);
            memoTextView = itemView.findViewById(R.id.text2);
        }
    }
}
