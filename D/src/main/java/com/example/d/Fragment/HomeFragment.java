package com.example.d.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.Adapter.HomeAdapter;
import com.example.d.Bean.DataBean;
import com.example.d.Bean.FoodBean;
import com.example.d.Net.BaseApp;
import com.example.d.Presenter.HomePresenter;
import com.example.d.R;
import com.example.d.View.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {


    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    Unbinder unbinder;
    private View view;
    private HomeAdapter adapter;
    private ArrayList<DataBean> list;
    private HomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        presenter = new HomePresenter(this);
        presenter.setData();
        rvHome.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        adapter = new HomeAdapter(list, getActivity());

        rvHome.setAdapter(adapter);
        adapter.setOnLongItemClick(new HomeAdapter.OnLongItemClick() {
            @Override
            public void onLongClick(int position) {
                insertData(position);
            }
        });
    }

    private void insertData(int position) {
        presenter.insert(list.get(position));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setData(FoodBean bean) {
        adapter.addData(bean.getData());
    }

    @Override
    public void setQuery(List<DataBean> datasBeans) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void renewal() {

    }
}
