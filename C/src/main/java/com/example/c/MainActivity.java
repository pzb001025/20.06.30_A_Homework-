package com.example.c;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c.p.RegisterPresenter;
import com.example.c.v.RegisterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {

    private EditText et_name;
    private EditText et_pas;
    private Button btn_que;
    private Button btn_qu;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new RegisterPresenter(this);
        initView();
        initPer();
    }

    private void initPer() {
        String[] pers = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, pers, 1);
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pas = (EditText) findViewById(R.id.et_pas);
        btn_que = (Button) findViewById(R.id.btn_que);
        btn_qu = (Button) findViewById(R.id.btn_qu);

        btn_que.setOnClickListener(this);
        btn_qu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_que:
                submit();
                break;
            case R.id.btn_qu:
                final Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        String pas = et_pas.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pas)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(name, pas);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            et_name.setText(name);
        }
    }

    @Override
    public void onSuccess(String s) {
        //成功的回调
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ListActivity.class));
        finish();
    }
}
