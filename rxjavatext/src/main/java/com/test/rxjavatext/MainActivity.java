package com.test.rxjavatext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ProgressBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_main);
        mBar = (ProgressBar) findViewById(R.id.pro_main);
        String[] names = {"Hi","Hello","Mack"};
        //Rxjava 测试
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.i("MainActivity",name);
                    }
                });

        //rxjava 测试加载图片,线程控制器
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                try {
                    URL url = new URL("http://bpic.588ku.com/templet_water_img/18/06/26/b89f9c4adeba120a9fb6237b62476a7d.jpg!/fw/800/unsharp/true/compress/true");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    subscriber.onNext(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })
            .observeOn(AndroidSchedulers.mainThread())   //指定subscriber的回调发生在UI线程
            .subscribeOn(Schedulers.newThread())         //指定subscribe()发生在新线程
            .subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {
                mBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                mBar.setVisibility(View.GONE);
            }

            @Override
            public void onNext(Bitmap bitmap) {
                if (bitmap != null) {
                    mImageView.setImageBitmap(bitmap);
                    onCompleted();
                }
            }
        });

    }
}
