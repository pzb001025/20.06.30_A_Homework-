package com.example.c.m;

import com.example.c.BackCallBack;
import com.example.c.BaseApp;
import com.example.c.Bean;
import com.example.c.db.BeanDao;

import java.util.List;

public class RegisterModel {

    private BeanDao dao = BaseApp.getInstance().getDaoSession().getBeanDao();
    ;

    public void register(int mPath, String name, String psd, String confirm_psd, BackCallBack<String> callBack) {
        final Bean bean = new Bean(null, name, psd, mPath);
        final long l = dao.insertOrReplace(bean);
        callBack.onSuccess("注册成功");
    }

    public void login(String name, String pas, BackCallBack<String> callBack) {
        final List<Bean> list = dao.queryBuilder().where(BeanDao.Properties.Name.eq(name), BeanDao.Properties.Pass.eq(pas)).list();
        if (list != null && list.size() > 0) {
            callBack.onSuccess("登录成功");
        } else {
            callBack.onFail("登录失败");
        }
    }
}
