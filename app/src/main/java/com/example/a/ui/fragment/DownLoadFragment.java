package com.example.a.ui.fragment;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.R;
import com.example.a.bean.EventBean;
import com.example.a.ui.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadFragment extends Fragment {

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.btn_start)
    Button btnStart;
    private View view;
    private Unbinder bind;
    private MyService myService;
    private DecimalFormat decimalFormat;
    public static FragmentActivity mfragmetn;
    private Intent intent;

    public DownLoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_down_load, container, false);
        bind = ButterKnife.bind(this, view);
        initPre();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        FragmentActivity activity = getActivity();
        mfragmetn = activity;
        decimalFormat = new DecimalFormat("0.00");

        intent = new Intent(getActivity(), MyService.class);
        MyConnection con = new MyConnection();
        getActivity().bindService(intent, con, Context.BIND_AUTO_CREATE);

        return view;
    }

    private void initPre() {
        final int i = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i == PackageManager.PERMISSION_DENIED) {
        } else {
            String[] pres = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            ActivityCompat.requestPermissions(getActivity(), pres, 100);
        }
    }


    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final MyService.MyBind myBind = (MyService.MyBind) service;
            myService = myBind.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                String mPath = "/storage/emulated/0/a.apk";
                myService.retrofitDownload(mPath);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiver(EventBean bean) {
        switch (bean.flg) {
            case 0:
                showToast("下载失败");
                break;
            case 1:
                setProgress(bean);
                break;
            default:
                break;
        }
    }

    private void setProgress(EventBean bean) {
        pb.setMax(bean.max);
        pb.setProgress(bean.progtess);

        float progress = bean.progtess * 100f / bean.max;
        final String format = decimalFormat.format(progress);
        tvText.setText("当前下载进度为：" + format + "%");
        if (bean.getTex() == 100) {
            showToast("下载完成");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

}
