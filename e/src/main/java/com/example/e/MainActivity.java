package com.example.e;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e.Adapter.VpAdapter;
import com.example.e.Bean.Bean;
import com.example.e.Bean.TabBean;
import com.example.e.Fragment.PublicFragment;
import com.example.e.Presenter.MainPresenter;
import com.example.e.View.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private TabLayout tab;
    private ViewPager vp;
    private Button btn;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private VpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        final MainPresenter presenter = new MainPresenter(this);
        presenter.setTab();
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);


        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        adapter = new VpAdapter(getSupportFragmentManager(), fragments, titles);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                break;
        }
    }

    @Override
    public void setTab(TabBean bean) {
        for (int i = 0; i < bean.getData().size(); i++) {
            fragments.add(new PublicFragment(bean.getData().get(i).getId()));
            titles.add(bean.getData().get(i).getName());
        }
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void setData(Bean bean) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
