package com.test.myapplication.Base;

public interface IBaseView {
    void configPersenter();
    <P extends BasePersenter> P getPersenter();
    <P extends BasePersenter> P creatPersenter(Class<P> pClass);
}
