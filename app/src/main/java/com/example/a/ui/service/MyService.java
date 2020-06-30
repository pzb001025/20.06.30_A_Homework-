package com.example.a.ui.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.a.bean.EventBean;
import com.example.a.net.ApiService;
import com.example.a.ui.fragment.DownLoadFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.core.app.ActivityCompat;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    public class MyBind extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initPre();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void initPre() {
        String[] pres = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(DownLoadFragment.mfragmetn, pres, 100);
    }


    public void retrofitDownload(String savaPath) {
        new Retrofit.Builder()
                .baseUrl(ApiService.downUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .download()
                .subscribeOn(Schedulers.io())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody body) {
                        InputStream inputStream = body.byteStream();
                        int max = (int) body.contentLength();
                        savaFile(savaPath, inputStream, max);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void savaFile(String savaPath, InputStream inputStream, int max) {
        byte[] bytes = new byte[1024];
        int len = 0;
        FileOutputStream fos = null;
        int count = 0;
        try {
            fos = new FileOutputStream(savaPath);
            while ((len = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
                count += len;
                Log.d(TAG, "savaFile: " + count);
                int l = (count / max) * 100;
                EventBus.getDefault().post(new EventBean(1, max, count, l));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
