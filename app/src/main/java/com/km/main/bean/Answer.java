package com.km.main.bean;

/**
 *
 * Created by yuan on 2017/3/21.
 */

public class Answer {

    private String account; //用户帐号
    private String icon_url;
    private String title,A,B,C;
    private int right_flag;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getRight_flag() {
        return right_flag;
    }

    public void setRight_flag(int right_flag) {
        this.right_flag = right_flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }
}
