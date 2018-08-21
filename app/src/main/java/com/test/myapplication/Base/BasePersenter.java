package com.test.myapplication.Base;

public class BasePersenter<V extends IBaseView> implements IBasePersenter {
    public V view;

    @Override
    public void start() {

    }

    public void setView(V view) {
        this.view = view;
    }
}
