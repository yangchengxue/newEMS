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
import com.example.ycx36.newems.util.getCarInfo;
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

    getCarInfo getCarInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        ButterKnife.bind(this);
        getCarInfo = new getCarInfo(this);
        SharedPreferences pref = getSharedPreferences("username",MODE_PRIVATE);
        String username = pref.getString("username","");
        SharedPreferences pref2 = getSharedPreferences("password",MODE_PRIVATE);
        String password = pref2.getString("password","");
        if (!username.equals("")){
            getCarInfo.getCarAllData_save(username,password);
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


}
