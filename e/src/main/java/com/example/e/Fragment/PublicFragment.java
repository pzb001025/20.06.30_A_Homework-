package com.example.e.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e.Adapter.RvAdapter;
import com.example.e.Bean.Bean;
import com.example.e.Bean.Event;
import com.example.e.Bean.TabBean;
import com.example.e.Presenter.MainPresenter;
import com.example.e.R;
import com.example.e.View.MainView;
import com.example.e.WebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicFragment extends Fragment implements MainView, OnRefreshLoadMoreListener {
    private int id;
    private int page = 1;
    private View view;
    private RecyclerView rv;
    private SmartRefreshLayout srl;
    private ArrayList<Bean.DataBean.DatasBean> list;
    private RvAdapter adapter;
    private MainPresenter presenter;

    @SuppressLint("ValidFragment")
    public PublicFragment(int id) {
        this.id = id;
    }

    public PublicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_public, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        presenter = new MainPresenter(this);
        presenter.setData(page, id);
        srl = (SmartRefreshLayout) view.findViewById(R.id.srl);
        rv = (RecyclerView) view.findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new RvAdapter(list, getActivity());
        rv.setAdapter(adapter);
        srl.setOnRefreshLoadMoreListener(this);
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), WebActivity.class));
                EventBus.getDefault().postSticky(new Event(list.get(position).getLink()));

            }
        });
    }


    @Override
    public void setTab(TabBean bean) {

    }

    @Override
    public void setData(Bean bean) {
        adapter.addData(bean.getData().getDatas());
        srl.finishRefresh();
        srl.finishLoadMore();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        presenter.setData(page, id);
        srl.finishLoadMore();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        list.clear();
        page = 1;
        presenter.setData(page, id);
        srl.finishRefresh();
        adapter.notifyDataSetChanged();
    }
}
