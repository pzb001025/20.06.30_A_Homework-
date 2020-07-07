package com.example.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RvAdapter extends RecyclerView.Adapter {
    public ArrayList<Bean.DataBean.DatasBean> list;
    private Context context;
    private int TYPE_ONE = 1;
    private int TYPE_TWO = 2;
    private final LayoutInflater from;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RvAdapter(ArrayList<Bean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
        from = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            final View view = from.inflate(R.layout.item_rv1, parent, false);
            return new ViewHolder1(view);
        } else {
            final View view = from.inflate(R.layout.item_rv2, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int viewType = holder.getItemViewType();
        final Bean.DataBean.DatasBean bean = list.get(position);
        if (viewType == TYPE_ONE) {
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.tv_title1.setText(bean.getTitle());
            Glide.with(context).load(bean.getEnvelopePic()).into(viewHolder1.iv_pic1);
        } else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.tv_title2.setText(bean.getTitle());
            Glide.with(context).load(bean.getEnvelopePic()).into(viewHolder2.iv_pic2);
        }
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

    @Override
    public int getItemViewType(int position) {
        final Bean.DataBean.DatasBean bean = list.get(position);
        final int id = bean.getId();
        if (id % 3 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    public void addData(Bean bean) {
        list.addAll(bean.getData().getDatas());
        notifyDataSetChanged();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        public ImageView iv_pic1;
        public TextView tv_title1;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.iv_pic1 = (ImageView) rootView.findViewById(R.id.iv_pic1);
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        public ImageView iv_pic2;
        public TextView tv_title2;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.iv_pic2 = (ImageView) rootView.findViewById(R.id.iv_pic2);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
