package com.example.ycx36.newems.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.getCarAllBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.regesterUserBean;
import com.example.ycx36.newems.util.requestGetCarAllBean;
import com.example.ycx36.newems.util.requestRegisterUserBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class fragment_CarStatus extends Fragment {

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_carstatus, container, false);
        }
        return view;
    }

}
