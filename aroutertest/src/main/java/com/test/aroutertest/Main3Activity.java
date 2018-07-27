package com.test.aroutertest;

import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    private ConstraintSet start = new ConstraintSet();
    private ConstraintSet end = new ConstraintSet();
    private ConstraintLayout mConstraintLayout;
    private boolean isSecond = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mConstraintLayout = findViewById(R.id.main);
        start.clone(mConstraintLayout);
        end.clone(this, R.layout.layout_test_one);

    }

    public void onApplyClick(View view) {
        if (!isSecond) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                TransitionManager.beginDelayedTransition(mConstraintLayout);
            end.applyTo(mConstraintLayout);
            isSecond = true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                TransitionManager.beginDelayedTransition(mConstraintLayout);
            start.applyTo(mConstraintLayout);
            isSecond = false;
        }
    }
}
