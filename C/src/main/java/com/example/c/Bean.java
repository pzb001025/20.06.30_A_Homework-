package com.example.c;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Bean {
    @Id
    private Long id;
    private String name;
    private String pass;
    private int imgpath;

    @Generated(hash = 1937208704)
    public Bean(Long id, String name, String pass, int imgpath) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.imgpath = imgpath;
    }

    @Generated(hash = 80546095)
    public Bean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getImgpath() {
        return this.imgpath;
    }

    public void setImgpath(int imgpath) {
        this.imgpath = imgpath;
    }
}
