package com.example.e.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.e.Bean.Bean;
import com.example.e.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2020/5/11.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private ArrayList<Bean.DataBean.DatasBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RvAdapter(ArrayList<Bean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Bean.DataBean.DatasBean datasBean = list.get(position);
        holder.tv_rv.setText(datasBean.getTitle());
        final RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .transform(new RoundedCorners(100));
        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.iv_rv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<Bean.DataBean.DatasBean> datas) {
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_rv;
        public TextView tv_rv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_rv = (ImageView) itemView.findViewById(R.id.iv_rv);
            this.tv_rv = (TextView) itemView.findViewById(R.id.tv_rv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
