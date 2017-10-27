package com.example.multichat.recycler;

import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.multichat.MsgInfo;
import com.example.multichat.R;
import com.example.multichat.databinding.ItemMessageMeBinding;
import com.example.multichat.databinding.ItemMessageYouBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by junsuk on 2017. 10. 27..
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ME = 0;
    public static final int TYPE_YOU = 1;

    private List<MsgInfo> mItems = new ArrayList<>();

    public void addItem(MsgInfo msgInfo) {
        mItems.add(msgInfo);
        notifyItemChanged(mItems.size() - 1);
    }

    @BindingConversion
    public static String convertLongToString(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        return dateFormat.format(new Date(time));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutRes = 0;
        if (viewType == TYPE_ME) {
            layoutRes = R.layout.item_message_me;
        } else if (viewType == TYPE_YOU) {
            layoutRes = R.layout.item_message_you;
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutRes, parent, false);

        if (viewType == TYPE_ME) {
            return new MessageMeViewHolder(view);
        } else if (viewType == TYPE_YOU) {
            return new MessageYouViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MsgInfo msgInfo = mItems.get(position);

        switch (msgInfo.getItemType()) {
            case TYPE_ME:
                ((MessageMeViewHolder) holder).binding.setMsg(msgInfo);
                break;
            case TYPE_YOU:
                ((MessageYouViewHolder) holder).binding.setMsg(msgInfo);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemType();
    }

    static class MessageMeViewHolder extends RecyclerView.ViewHolder {

        ItemMessageMeBinding binding;

        public MessageMeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    static class MessageYouViewHolder extends RecyclerView.ViewHolder {

        ItemMessageYouBinding binding;

        public MessageYouViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
