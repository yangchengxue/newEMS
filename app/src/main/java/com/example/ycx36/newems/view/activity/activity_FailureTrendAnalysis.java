package com.example.ycx36.newems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.view.activity.activity_TrendAnalysis.activity_bms;
import com.example.ycx36.newems.view.activity.activity_TrendAnalysis.activity_cpu;
import com.example.ycx36.newems.view.activity.activity_TrendAnalysis.activity_mcu;
import com.example.ycx36.newems.view.activity.activity_TrendAnalysis.activity_rcm;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_FailureTrendAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__failure_trend_analysis);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RL_1:
                startActivity(new Intent(this, activity_bms.class));
                break;
            case R.id.RL_2:
                startActivity(new Intent(this, activity_mcu.class));
                break;
            case R.id.RL_3:
                startActivity(new Intent(this, activity_cpu.class));
                break;
            case R.id.RL_4:
                startActivity(new Intent(this, activity_rcm.class));
                break;
        }
    }
}
