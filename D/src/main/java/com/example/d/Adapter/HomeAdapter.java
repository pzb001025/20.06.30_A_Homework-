package com.example.d.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.d.Bean.DataBean;
import com.example.d.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2020/5/10.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<DataBean> list;
    private Context context;
    private int VIEW_ONE = 1;
    private int VIEW_TWO = 2;
    private OnLongItemClick onLongItemClick;

    public void setOnLongItemClick(OnLongItemClick onLongItemClick) {
        this.onLongItemClick = onLongItemClick;
    }

    public HomeAdapter(ArrayList<DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_ONE) {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_rv1, parent, false);
            return new ViewHolder(view);
        } else {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_rv2, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final DataBean dataBean = list.get(position);
        final int itemViewType = holder.getItemViewType();
        if (itemViewType == VIEW_ONE) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_rv1.setText(dataBean.getTitle());
            final RequestOptions placeholder = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);
            Glide.with(context).load(dataBean.getPic()).apply(placeholder).into(viewHolder.iv_rv);
        } else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.tv_rv2.setText(dataBean.getTitle());
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongItemClick != null) {
                    onLongItemClick.onLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEW_ONE;
        } else {
            return VIEW_TWO;
        }
    }

    public void addData(List<DataBean> datas) {
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public void addQuery(List<DataBean> datasBeans) {
        list.addAll(datasBeans);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_rv;
        public TextView tv_rv1;

        public ViewHolder(View rootView) {
            super(rootView);
            this.iv_rv = (ImageView) rootView.findViewById(R.id.iv_rv);
            this.tv_rv1 = (TextView) rootView.findViewById(R.id.tv_rv1);
        }

    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView tv_rv2;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.tv_rv2 = (TextView) rootView.findViewById(R.id.tv_rv2);
        }
    }

    public interface OnLongItemClick {
        void onLongClick(int position);
    }
}
