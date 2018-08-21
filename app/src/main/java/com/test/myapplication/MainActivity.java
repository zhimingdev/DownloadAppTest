package com.test.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import top.defaults.view.DateTimePickerView;

public class MainActivity extends AppCompatActivity {

    TextView tv_date = null;
    TextView tv_time = null;
    int year = 2016;
    int month = 10;
    int day = 8;
    int houre = 15;
    int minute = 20;
    private DateTimePickerView dateTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_date = (TextView) findViewById(R.id.dialog_tv_date);
        tv_time = (TextView) findViewById(R.id.dialog_tv_time);
    }

    // 点击事件,湖区日期
    public void getDate(View v) {

        DatePickerDialog datePicker =
                new DatePickerDialog(MainActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this,
                                year + "year " + (monthOfYear + 1) + "month " + dayOfMonth + "day",
                                Toast.LENGTH_SHORT).show();

                        StringBuffer sb = new StringBuffer();

                        sb.append(String.format("%d-%02d-%02d", datePicker.getYear(), datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));


                    }
                }, 2014, 1, 7);
        datePicker.show();
    }

    // 点击事件,湖区日期
    public void getTime(View v) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                houre = hourOfDay;
                MainActivity.this.minute = minute;
            }
        }, 15, 20, true).show();
        showTime();
    }

    // 显示选择日期
    private void showDate() {
        tv_date.setText("你选择的日期是：" + year + "年" + month + "月" + day + "日");
    }

    // 显示选择日期
    private void showTime() {
        tv_time.setText("你选择的时间是：" + houre + "时" + minute + "分");
    }
}
