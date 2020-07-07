package com.example.c;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.c.p.RegisterPresenter;
import com.example.c.v.RegisterView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {

    private ImageView iv;
    private EditText et_name;
    private EditText et_psd;
    private EditText et_confirm_psd;
    private Button btn_register;
    private RegisterPresenter presenter;
    private String name;
    private String psd;
    private String confirm_psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter(this);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        et_name = (EditText) findViewById(R.id.et_name);
        et_psd = (EditText) findViewById(R.id.et_psd);
        et_confirm_psd = (EditText) findViewById(R.id.et_confirm_psd);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);

        Glide.with(this).load(R.drawable.ic_launcher_background).into(iv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        name = et_name.getText().toString().trim();
        psd = et_psd.getText().toString().trim();
        confirm_psd = et_confirm_psd.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psd) || TextUtils.isEmpty(confirm_psd)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!psd.equals(confirm_psd)) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.register(R.drawable.ic_launcher_background, name, psd, confirm_psd);
    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        final Intent intent = new Intent();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
