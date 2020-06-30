package com.example.a.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a.R;
import com.example.a.bean.HomeBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RvAdapter extends RecyclerView.Adapter {
    public ArrayList<HomeBean.DataBean.DatasBean> list;
    private Context context;

    public RvAdapter(ArrayList<HomeBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final HomeBean.DataBean.DatasBean bean = list.get(position);
        Vh vh = (Vh) holder;
        vh.tvTitle.setText(bean.getTitle());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context).load(bean.getEnvelopePic()).apply(options).into(vh.ivPic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(HomeBean homeBean) {
        list.addAll(homeBean.getData().getDatas());
        notifyDataSetChanged();
    }

    class Vh extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        Vh(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
