package com.example.c.p;

import android.util.Log;
import android.widget.Toast;

import com.example.c.BackCallBack;
import com.example.c.m.RegisterModel;
import com.example.c.v.RegisterView;

public class RegisterPresenter {
    private RegisterView view;
    private final RegisterModel model;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
        model = new RegisterModel();
    }

    public void register(int mPath, String name, String psd, String confirm_psd) {
        model.register(mPath, name, psd, confirm_psd, new BackCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public void login(String name, String pas) {
        model.login(name,pas, new BackCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                Log.d("TAG", "onFail: "+msg);
            }
        });
    }
}
