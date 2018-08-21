package com.test.myapplication.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.myapplication.R;

import java.util.List;

public abstract class BaseMvpActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView{

    private List<BasePersenter> mPersenterList;
    private BasePersenter mBasePersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configPersenter();
        setContentView(R.layout.activity_main);
    }

    @Override
    public <P extends BasePersenter> P getPersenter() {
        return (P)mBasePersenter;
    }

    @Override
    public <P extends BasePersenter> P creatPersenter(Class<P> pClass) {
        try {
            mBasePersenter = pClass.newInstance();
            mBasePersenter.setView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
