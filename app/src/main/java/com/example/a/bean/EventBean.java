package com.example.a.bean;

public class EventBean {
    public int flg;
    public int max;
    public int progtess;
    public int tex;

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgtess() {
        return progtess;
    }

    public void setProgtess(int progtess) {
        this.progtess = progtess;
    }

    public int getTex() {
        return tex;
    }

    public void setTex(int tex) {
        this.tex = tex;
    }

    public EventBean(int flg, int max, int progtess, int tex) {
        this.flg = flg;
        this.max = max;
        this.progtess = progtess;
        this.tex = tex;
    }
}
