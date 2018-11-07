package com.forwork.com.forwork.bean;

import com.forwork.com.forwork.bean.base.BaseResult;
import com.forwork.com.forwork.bean.base.Geek;
import com.forwork.com.forwork.bean.base.Product;

import java.util.List;

/**
 * Created by Administrator on 2018/11/1.
 */

public class IndexBean1 extends BaseResult{
    private DataBean data;
    private Object popup;
    private Geek geek;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getPopup() {
        return popup;
    }

    public void setPopup(Object popup) {
        this.popup = popup;
    }

    public Geek getGeek() {
        return geek;
    }

    public void setGeek(Geek geek) {
        this.geek = geek;
    }

    public static class DataBean {
        private String total;
        private int page_size;
        private int page;
        private List<Product> items;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Product> getItems() {
            return items;
        }

        public void setItems(List<Product> items) {
            this.items = items;
        }
    }
}
