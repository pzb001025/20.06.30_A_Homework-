package com.example.a.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.R;
import com.example.a.bean.HomeBean;
import com.example.a.presenter.HomePresenter;
import com.example.a.ui.adapter.RvAdapter;
import com.example.a.view.HomeView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    private View view;
    private Unbinder bind;
    private RvAdapter adapter;
    private HomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        bind = ButterKnife.bind(this, view);
        presenter = new HomePresenter(this);
        initView();
        initData();
        return view;
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        rlvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rlvHome.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        final ArrayList<HomeBean.DataBean.DatasBean> list = new ArrayList<>();
        adapter = new RvAdapter(list, getContext());
        rlvHome.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void setData(HomeBean homeBean) {
        if (homeBean.getData() != null &&
                homeBean.getData().getDatas() != null &&
                homeBean.getData().getDatas().size() > 0) {
            adapter.addData(homeBean);
        }
    }
}
