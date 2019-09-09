package com.example.ycx36.newems.view.activity.activity_TrendAnalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.ycx36.newems.R;
import com.github.mikephil.charting.charts.LineChart;

public class activity_bms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bms);
        LineChart chart = (LineChart) findViewById(R.id.chart);


    }
}
