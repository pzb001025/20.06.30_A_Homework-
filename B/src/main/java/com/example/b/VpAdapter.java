package com.example.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class VpAdapter extends PagerAdapter {
    private ArrayList<Bean.DataBean.DatasBean> list;
    private Context context;

    public VpAdapter(ArrayList<Bean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_vp, null);
        final ImageView iv_vp = view.findViewById(R.id.iv_vp);
        final TextView tv_vp = view.findViewById(R.id.tv_vp);
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(iv_vp);
        tv_vp.setText("第" + (position + 1) + "张/共" + list.size() + "张");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
