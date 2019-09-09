package com.example.ycx36.newems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ycx36.newems.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

//违章检测
public class activity_violationDetection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_detection);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_header_back2:
                    finish();
                break;
        }
    }
}
