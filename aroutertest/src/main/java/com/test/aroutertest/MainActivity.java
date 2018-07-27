package com.test.aroutertest;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.test.aroutertest.base.BaseActivity;

@Route(path = "/aroutertest/MainActivity")
public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_main);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build( "/aroutertest/Main2Activity")
                        .withString("name","小明")
                        .withTransition(R.anim.activity_in,R.anim.activity_out)
                        .navigation();
            }
        });
    }

    @Override
    public void initData() {

    }
}
