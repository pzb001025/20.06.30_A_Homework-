package com.example.d;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.Bean.DataBean;

import java.io.Serializable;

public class UpActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_up;
    private EditText et_text;
    private Button btn_up;
    private DataBean data;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);
        initView();
    }

    private void initView() {
        iv_up = (ImageView) findViewById(R.id.iv_up);
        et_text = (EditText) findViewById(R.id.et_text);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_up.setOnClickListener(this);

        intent = getIntent();
        data = (DataBean) intent.getSerializableExtra("data");
        Glide.with(this).load(data.getPic()).into(iv_up);
        et_text.setText(data.getTitle());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_up:
                final String et = et_text.getText().toString();
                data.setTitle(et);
                intent.putExtra("da", data);
                setResult(2, intent);
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String text = et_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
