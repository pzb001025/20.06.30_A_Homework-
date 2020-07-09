package com.example.d.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class DataBean implements Serializable {
    public static final long serialVersionUID = 1L;
    /**
     * id : 8289
     * title : 油焖大虾
     * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg
     * collect_num : 1670
     * food_str : 大虾 葱 生姜 植物油 料酒
     * num : 1670
     */
    @Id
    private String id;
    private String title;
    private String pic;
    private String collect_num;
    private String food_str;
    private int num;

    @Generated(hash = 578859743)
    public DataBean(String id, String title, String pic, String collect_num,
                    String food_str, int num) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.collect_num = collect_num;
        this.food_str = food_str;
        this.num = num;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCollect_num() {
        return this.collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getFood_str() {
        return this.food_str;
    }

    public void setFood_str(String food_str) {
        this.food_str = food_str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}