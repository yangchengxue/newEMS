package com.example.ycx36.newems.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.getCarAllBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.requestGetCarAllBean;
import com.example.ycx36.newems.view.activity.ExaminationActivity;
import com.example.ycx36.newems.view.activity.activity_carInfo;
import com.example.ycx36.newems.view.activity.activity_ownerPosition;
import com.example.ycx36.newems.view.activity.activity_violationDetection;
import com.github.lzyzsd.circleprogress.ArcProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class fragment_Examination extends Fragment {

    @BindView(R.id.arc_progress) ArcProgress arcProgressBar;
    @BindView(R.id.bt_examination) Button bt_examination;


    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_examination, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    public void init(){
        int mucFraction = getActivity().getSharedPreferences("ExaminationFraction",MODE_PRIVATE).getInt("muc",0);
        arcProgressBar.setProgress(mucFraction);
    }

    @OnClick({R.id.arc_progress,R.id.bt_examination,R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5,R.id.l6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arc_progress:

                break;
            case R.id.bt_examination: //点击开始体检(已隐藏)
                break;
            case R.id.l1:
                intentNext(1);
                break;
            case R.id.l2:
                intentNext(2);
                break;
            case R.id.l3:
                intentNext(3);
                break;
            case R.id.l4:
                startActivity(new Intent(getActivity(), activity_carInfo.class));
                break;
            case R.id.l5:
                startActivity(new Intent(getActivity(), activity_violationDetection.class));
                break;
            case R.id.l6:
                startActivity(new Intent(getActivity(), activity_ownerPosition.class));
                break;
        }
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void intentNext(int flag_examination){
        Intent intent = new Intent(getActivity(),ExaminationActivity.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putInt("flag_examination", flag_examination);         //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的
        startActivity(intent);
    }

}
