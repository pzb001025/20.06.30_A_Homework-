package com.example.d;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    private int time = 5;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_main);
        animation.setDuration(5000);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (time > 0) {
                tv.setText("" + time);
                time--;
            } else {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                timer.cancel();
                finish();
            }
        }
    };
}
