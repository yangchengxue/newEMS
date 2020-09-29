package com.example.ycx36.newems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ycx36.newems.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_carHistroy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4,R.id.tv_header_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RL_1:
                startActivity(new Intent(activity_carHistroy.this,activity_from_carHistroy_bms.class));
                break;
            case R.id.RL_2:
                break;
            case R.id.RL_3:

                break;
            case R.id.RL_4:
                break;
            case R.id.tv_header_back:
                finish();
                break;
        }
    }
}
