package com.example.b;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class VpActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        initView();
    }

    private void initView() {
        final Intent intent = getIntent();
        ArrayList<Bean.DataBean.DatasBean> list = (ArrayList<Bean.DataBean.DatasBean>) intent.getSerializableExtra("data");
        final int position = intent.getIntExtra("position", 0);
        vp = (ViewPager) findViewById(R.id.vp);
        final VpAdapter adapter = new VpAdapter(list, this);
        vp.setAdapter(adapter);
        vp.setCurrentItem(position);
    }
}
