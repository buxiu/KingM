package com.km.main.bean;

/**
 *
 * Created by yuan on 2017/3/21.
 */

public class Answer {

    private String title,A,B,C;
    private int right_flag;

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
