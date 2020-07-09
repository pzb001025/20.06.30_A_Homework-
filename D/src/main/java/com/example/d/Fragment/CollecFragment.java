package com.example.d.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.Adapter.HomeAdapter;
import com.example.d.Bean.DataBean;
import com.example.d.Bean.FoodBean;
import com.example.d.Net.BaseApp;
import com.example.d.Presenter.HomePresenter;
import com.example.d.R;
import com.example.d.UpActivity;
import com.example.d.View.HomeView;
import com.example.d.db.DataBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollecFragment extends Fragment implements HomeView {


    private View view;
    private ArrayList<DataBean> list;
    private HomeAdapter adapter;
    private HomePresenter presenter;
    private int mposition;
    private RecyclerView rv_collec;


    public CollecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collec, container, false);
        initView(view);
        return view;
    }


    private void initData() {
        presenter.query();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
            initView(view);
        }
    }

    private void initView(View view) {
        presenter = new HomePresenter(this);
        rv_collec = (RecyclerView) view.findViewById(R.id.rv_collec);
        rv_collec.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rv_collec.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        adapter = new HomeAdapter(list, getActivity());

        rv_collec.setAdapter(adapter);
        registerForContextMenu(rv_collec);
        adapter.setOnLongItemClick(new HomeAdapter.OnLongItemClick() {
            @Override
            public void onLongClick(int position) {
                mposition = position;
            }
        });
        initData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                presenter.delete(list.get(mposition));
                break;
            case 1:
                final DataBean dataBean = list.get(mposition);
                final Intent intent = new Intent(getActivity(), UpActivity.class);
                intent.putExtra("data", dataBean);
                startActivityForResult(intent, 1);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            final DataBean da = (DataBean) data.getSerializableExtra("da");
            list.set(mposition, da);
            final DataBeanDao dataBeanDao = BaseApp.getInstance().getDaoSession().getDataBeanDao();
            dataBeanDao.update(da);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setData(FoodBean bean) {

    }

    @Override
    public void setQuery(List<DataBean> datasBeans) {
        adapter.addQuery(datasBeans);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void renewal() {
        list.remove(mposition);
        adapter.notifyDataSetChanged();
    }
}
