package com.example.b;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b.p.MainPresenter;
import com.example.b.v.MainView;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView tv_toolbar;
    private Toolbar toolbar;
    private RecyclerView rv;
    private RvAdapter adapter;
    private MainPresenter presenter;
    private MainReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);

        toolbar.setTitle("");
        tv_toolbar.setText("首页");
        setSupportActionBar(toolbar);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        final ArrayList<Bean.DataBean.DatasBean> list = new ArrayList<>();
        adapter = new RvAdapter(list, this);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final Intent intent = new Intent("a");
                intent.putExtra("position", position);
                intent.putExtra("data", adapter.list);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public void setData(Bean bean) {
        if (bean.getData() != null && bean.getData().getDatas() != null && bean.getData().getDatas().size() > 0) {
            adapter.addData(bean);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new MainReceiver();
        IntentFilter filter = new IntentFilter("a");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
