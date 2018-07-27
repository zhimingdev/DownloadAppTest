package com.test.mvptest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.mvptest.R;
import com.test.mvptest.model.data.MyDepositModel;
import com.test.mvptest.persenter.MainPersenter;

public class MainActivity extends AppCompatActivity implements IMainView{

    private MainPersenter mMainPersenter;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.one_textview);
        mMainPersenter = new MainPersenter(this);
        mMainPersenter.requestMoth();
    }

    @Override
    public void upData(MyDepositModel model) {
        mTextView.setText(model.isAuth);
    }

}
