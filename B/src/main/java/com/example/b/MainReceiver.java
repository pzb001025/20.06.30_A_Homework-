package com.example.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int position = intent.getIntExtra("position", 0);
        ArrayList<Bean.DataBean.DatasBean> list = (ArrayList<Bean.DataBean.DatasBean>) intent.getSerializableExtra("data");
        Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();

        final Intent intent1 = new Intent(context, VpActivity.class);
        intent1.putExtra("position", position);
        intent1.putExtra("data", list);
        context.startActivity(intent1);
    }
}
