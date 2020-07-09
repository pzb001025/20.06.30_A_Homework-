package com.example.d;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.d.Fragment.CollecFragment;
import com.example.d.Fragment.HomeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private int mposition;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        fm = getSupportFragmentManager();

        initTitle();
        initFragment();
        initTab();

        fm.beginTransaction().add(R.id.fl, fragments.get(0)).commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchFragment(int position) {
        ft = fm.beginTransaction();

        final Fragment showFragment = fragments.get(position);
        final Fragment hideFragment = fragments.get(mposition);
        if (!showFragment.isAdded()) {
            ft.add(R.id.fl, showFragment);
        }
        ft.show(showFragment);
        ft.hide(hideFragment);
        ft.commit();
        mposition = position;
    }

    private void initTab() {
        for (int i = 0; i < titles.size(); i++) {
            tab.addTab(tab.newTab().setText(titles.get(i)));

        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollecFragment());
    }

    private void initTitle() {
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("收藏");
    }
}
