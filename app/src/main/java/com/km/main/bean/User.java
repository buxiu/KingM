package com.km.main.bean;

/**
 *
 * 用户
 * Created by yuan on 2017/3/19.
 */

public class User {
    private String account; //用户帐号
    private String tooken;  //用户密码

    public String getTooken() {
        return tooken;
    }

    public void setTooken(String tooken) {
        this.tooken = tooken;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
