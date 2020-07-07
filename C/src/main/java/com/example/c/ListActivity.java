package com.example.c;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.c.db.BeanDao;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    private ArrayList<Bean> mList;
    private RlvAdapter rlvAdapter;
    private int p;
    private Bean bean;
    private BeanDao dao;
    private PopupWindow pop;
    private EditText et;
    private Button btn_ok;
    private Button btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dao = BaseApp.getInstance().getDaoSession().getBeanDao();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mList = new ArrayList<>();
        rlvAdapter = new RlvAdapter(this, mList);
        rlv.setAdapter(rlvAdapter);
        registerForContextMenu(rlv);
        rlvAdapter.setOnItemLongClickListener(new RlvAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(int positon) {
                p = positon;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                bean = mList.get(p);
                dao.delete(bean);
                mList.remove(p);
                rlvAdapter.notifyDataSetChanged();
                break;
            case 1:
                upData();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void upData() {
        final View view = LayoutInflater.from(this).inflate(R.layout.layout_pop, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        pop.showAtLocation(rlv, Gravity.CENTER, 0, 0);

        et = (EditText) view.findViewById(R.id.et);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        btn_no = (Button) view.findViewById(R.id.btn_no);
        btn_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                final String et = this.et.getText().toString();
                mList.get(p).setName(et);
                rlvAdapter.notifyDataSetChanged();
                pop.dismiss();
                break;
            case R.id.btn_no:
                pop.dismiss();
                break;
        }
    }

    private void initData() {
        final BeanDao beanDao = BaseApp.getInstance().getDaoSession().getBeanDao();
        final List<Bean> list = beanDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            rlvAdapter.notifyDataSetChanged();
        }
    }
}
