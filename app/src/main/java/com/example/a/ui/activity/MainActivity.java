package com.example.a.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.a.R;
import com.example.a.ui.fragment.DownLoadFragment;
import com.example.a.ui.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private FragmentManager fm;
    private int mPosition;
    public static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initToolbar();
        mActivity = this;
    }

    private void initToolbar() {
        toolbar.setTitle(titles.get(0));
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl, fragments.get(0)).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(tab.getText());
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
        final FragmentTransaction ft = fm.beginTransaction();
        final Fragment showFragment = fragments.get(position);
        final Fragment hideFragment = fragments.get(mPosition);
        if (!showFragment.isAdded()) {
            ft.add(R.id.fl, showFragment);
        }
        ft.show(showFragment);
        ft.hide(hideFragment);
        ft.commit();
        mPosition = position;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DownLoadFragment());
        titles.add("首页");
        titles.add("下载");

        for (int i = 0; i < titles.size(); i++) {
            tab.addTab(tab.newTab().setText(titles.get(i)));
        }
    }
}
