package com.example.ycx36.newems.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.recyclerview.adapter_CurrentInfo;
import com.example.ycx36.newems.recyclerview.muc_data;
import com.example.ycx36.newems.util.getCarAllBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.requestGetCarAllBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class activity_CurrentInformation extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_CurrentInfo adapter;

    private ArrayList<muc_data> mucData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__current_information);
        ButterKnife.bind(this);
        initdata();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                    Thread.sleep(20000);
                    login();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initdata(){
        String motorspeed = getSharedPreferences("MOTORSPEED",MODE_PRIVATE).getString("motorspeed","");
        String motortorque = getSharedPreferences("MOTOTTORQUE",MODE_PRIVATE).getString("motortorque","");
        String totalcurrent = getSharedPreferences("TOTALCURRENT",MODE_PRIVATE).getString("totalcurrent","");
        String totalvoltage = getSharedPreferences("TOTALVOLTAGE",MODE_PRIVATE).getString("totalvoltage","");
        muc_data data1 = new muc_data("电机转速",motorspeed,"-15000-15000");
        muc_data data2 = new muc_data("电机转矩",motortorque,"-5000-5000");
        muc_data data3 = new muc_data("电机电流",totalcurrent,"1：正常 0：不正常");
        muc_data data4 = new muc_data("电机电压",totalvoltage,"1：正常 0：不正常");
        mucData.add(data1);
        mucData.add(data2);
        mucData.add(data3);
        mucData.add(data4);
        /**获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
        recyclerView.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new adapter_CurrentInfo(mucData); //获取适配器实例
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //调用系统横向分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));  //调用系统纵向分割线
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:
                finish();
                break;
        }
    }

    public void login(){
        requestGetCarAllBean requestGetCarAll = new requestGetCarAllBean();
        requestGetCarAll.setRequest(new requestGetCarAllBean.RequestBean());
        requestGetCarAll.getRequest().setCommon(new requestGetCarAllBean.RequestBean.CommonBean());
        requestGetCarAll.getRequest().getCommon().setAction("getCarAll");      //设置第一参数 "action":"getCarAll"
        requestGetCarAll.getRequest().getCommon().setReqtime("20190326171001");    //设置第二参数 "reqtime":"20190325180230"

        requestGetCarAll.getRequest().setContent(new requestGetCarAllBean.RequestBean.ContentBean());
        requestGetCarAll.getRequest().getContent().setId(null);      //设置id
        requestGetCarAll.getRequest().getContent().setName("admin");   //设置用户名
        requestGetCarAll.getRequest().getContent().setPassword("admin");  //设置用户密码
        requestGetCarAll.getRequest().getContent().setPhone("");    //设置手机号

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.79.62.86:10003/")
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
        rxjavaService.getCarAll(requestGetCarAll)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<getCarAllBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("错误          ",e.getMessage());
                    }

                    @Override
                    public void onNext(getCarAllBean data) {
                        if (data.getResponse().getContent() != null) {
                            //存储信息
                            Log.d("testyyyyy1",""+data.getResponse().getContent().getUid());
                            Log.d("testyyyyy2",""+data.getResponse().getContent().getUpdatetime());
                            Log.d("testyyyyy3",""+data.getResponse().getContent().getMotorspeed());

                            saveUserData("UID","uid",data.getResponse().getContent().getUid());
                            saveUserData("UPDATETIME","updatetime",data.getResponse().getContent().getUpdatetime());
                            saveUserData("MOTORSPEED","motorspeed",data.getResponse().getContent().getMotorspeed());
                            saveUserData("MOTOTTORQUE","motortorque",data.getResponse().getContent().getMotortorque());
                            saveUserData("TOTALCURRENT","totalcurrent",data.getResponse().getContent().getTotalcurrent());
                            saveUserData("TOTALVOLTAGE","totalvoltage",data.getResponse().getContent().getTotalvoltage());

                            Log.d("jkhgfg","4546465");
                            mucData.clear();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initdata();
                                }
                            });

                        }else {
                            //Toast.makeText(ExaminationActivity.this,"内容为空",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void saveUserData(String NAME,String name,String data){
        SharedPreferences.Editor editor = getSharedPreferences(NAME,MODE_PRIVATE).edit();
        editor.putString(name,data);
        editor.apply();
    }
}
