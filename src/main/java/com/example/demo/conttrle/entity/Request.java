package com.example.demo.conttrle.entity;

import lombok.Data;


public class Request {
    private int id;
    private AppUser appUser;
    private String qingqiu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getQingqiu() {
        return qingqiu;
    }

    public void setQingqiu(String qingqiu) {
        this.qingqiu = qingqiu;
    }
}
