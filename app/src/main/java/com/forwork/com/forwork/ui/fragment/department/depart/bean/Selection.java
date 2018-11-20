package com.forwork.com.forwork.ui.fragment.department.depart.bean;

/**
 * Created by Administrator on 2018/11/20.
 */

public class Selection {
    private int id;
    private String title;

    public Selection(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
