package com.example.ycx36.newems.view.activity;


import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.getCarAllBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.requestGetCarAllBean;
import com.example.ycx36.newems.view.sonfragment.fragment_baogao;
import com.example.ycx36.newems.view.sonfragment.fragment_jiance;
import com.example.ycx36.newems.view.sonfragment.fragment_jiance_2;
import com.example.ycx36.newems.view.sonfragment.fragment_jiance_3;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExaminationActivity extends AppCompatActivity {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.viewpagertab) SmartTabLayout viewPagerTab;
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        ButterKnife.bind(this);
        SharedPreferences pref = getSharedPreferences("UID",MODE_PRIVATE);
        String value = pref.getString("uid","");
        if (!value.equals("")){
            login();
        }else{
            Toast.makeText(this,"你还未登录",Toast.LENGTH_SHORT).show();
            finish();
        }

        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        int flag_examination = bundle.getInt("flag_examination");
        switch (flag_examination){
            case 1:
                tv_header_centerText.setText("常规检测");
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add("检测", fragment_jiance.class)
                        .add("报告", fragment_baogao.class)
                        .create());
                break;
            case 2:
                tv_header_centerText.setText("安全检测");
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add("检测", fragment_jiance_2.class)
                        .add("报告", fragment_baogao.class)
                        .create());
                break;
            case 3:
                tv_header_centerText.setText("故障检测");
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add("检测", fragment_jiance_3.class)
                        .add("报告", fragment_baogao.class)
                        .create());
                break;
        }
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
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
                                saveUserData("UID","uid",data.getResponse().getContent().getUid());
                                saveUserData("UPDATETIME","updatetime",data.getResponse().getContent().getUpdatetime());
                                saveUserData("MOTORSPEED","motorspeed",data.getResponse().getContent().getMotorspeed());
                                saveUserData("MOTOTTORQUE","motortorque",data.getResponse().getContent().getMotortorque());
                                saveUserData("TOTALCURRENT","totalcurrent",data.getResponse().getContent().getTotalcurrent());
                                saveUserData("TOTALVOLTAGE","totalvoltage",data.getResponse().getContent().getTotalvoltage());
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
