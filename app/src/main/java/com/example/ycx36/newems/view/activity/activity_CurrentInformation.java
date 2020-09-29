package com.example.ycx36.newems.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.recyclerview.adapter_CurrentInfo;
import com.example.ycx36.newems.recyclerview.muc_data;
import com.example.ycx36.newems.util.getCarInfo;


import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_CurrentInformation extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    LinearLayoutManager linearLayoutManager;
    adapter_CurrentInfo adapter;
    private getCarInfo getCarInfo;
    String username;
    String password;
    private ArrayList<muc_data> mucData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__current_information);
        ButterKnife.bind(this);
        SharedPreferences pref = getSharedPreferences("username", MODE_PRIVATE);
        username = pref.getString("username", "");
        SharedPreferences pref2 = getSharedPreferences("password", MODE_PRIVATE);
        password = pref2.getString("password", "");

        getCarInfo = new getCarInfo(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        assert bundle != null;
        switch (Objects.requireNonNull(bundle.getString("FromWhich"))){
            case "1":
//                initdata_BMS();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true){
                                getCarInfo.getCarAllData_save(username, password);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mucData.clear();
                                        initdata_BMS();
                                    }
                                });
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case "2":
//                initdata_MCU();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true){
                                getCarInfo.getCarAllData_save(username, password);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mucData.clear();
                                        initdata_MCU();
                                    }
                                });
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case "3":
                break;
            case "4":
//                initdata_VCU();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true){
                                getCarInfo.getCarAllData_save(username, password);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mucData.clear();
                                        initdata_VCU();
                                    }
                                });
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }


    }

    private void initdata_BMS(){
        tv_header_centerText.setText("BMS当前参数");
        String voltage = getSharedPreferences("VOLTAGE",MODE_PRIVATE).getString("voltage","");
        String current = getSharedPreferences("CURRENT",MODE_PRIVATE).getString("current","");
        String aftercurrent = getSharedPreferences("AFTERCURRENT",MODE_PRIVATE).getString("aftercurrent","");
        String Unitvm = getSharedPreferences("UNITVM",MODE_PRIVATE).getString("Unitvm","");
        String Unitid = getSharedPreferences("UNITID",MODE_PRIVATE).getString("Unitid","");
        String Unitvmlow = getSharedPreferences("UNITVMLOW",MODE_PRIVATE).getString("Unitvmlow","");
        String Unittemid = getSharedPreferences("UNITTEMID",MODE_PRIVATE).getString("Unittemid","");
        String Unittem = getSharedPreferences("UNITTEM",MODE_PRIVATE).getString("Unittem","");
        String Unitidlow = getSharedPreferences("UNITIDLOW",MODE_PRIVATE).getString("Unitidlow","");
        String Unittemlow = getSharedPreferences("UNITTEMLOW",MODE_PRIVATE).getString("Unittemlow","");

        muc_data data1 = new muc_data("总电压",voltage,"[0,800]");
        muc_data data2 = new muc_data("总电流",current,"[-500,500]");
        muc_data data3 = new muc_data("剩余电流",aftercurrent,"[0,100]");
        muc_data data4 = new muc_data("最高单体电压",Unitvm,"[0,6]");
        muc_data data5 = new muc_data("最低单体电压序号",Unitid,"[0,250]");
        muc_data data6 = new muc_data("最低单体电压",Unitvmlow,"[0,6]");
        muc_data data7 = new muc_data("最高单体温度探针序号",Unittemid,"[0,250]");
        muc_data data8 = new muc_data("最高电池单体温度",Unittem,"[-50,154]");
        muc_data data9 = new muc_data("最低单体温度探针序号",Unitidlow,"[0,250]");
        muc_data data10 = new muc_data("最低电池单体温度",Unittemlow,"[-50,154]");

        mucData.add(data1);
        mucData.add(data2);
        mucData.add(data3);
        mucData.add(data4);
        mucData.add(data5);
        mucData.add(data6);
        mucData.add(data7);
        mucData.add(data8);
        mucData.add(data9);
        mucData.add(data10);

        /**获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
        recyclerView.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new adapter_CurrentInfo(mucData); //获取适配器实例
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //调用系统横向分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));  //调用系统纵向分割线
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    private void initdata_MCU(){
        tv_header_centerText.setText("MCU当前参数");
        String motorspeed = getSharedPreferences("MOTORSPEED",MODE_PRIVATE).getString("motorspeed","");
        String motortorque = getSharedPreferences("MOTOTTORQUE",MODE_PRIVATE).getString("motortorque","");
        String totalcurrent = getSharedPreferences("TOTALCURRENT",MODE_PRIVATE).getString("totalcurrent","");
        String totalvoltage = getSharedPreferences("TOTALVOLTAGE",MODE_PRIVATE).getString("totalvoltage","");
        String motortem = getSharedPreferences("MOTORTEM",MODE_PRIVATE).getString("motortem","");
        String motorvm = getSharedPreferences("MOTORVM",MODE_PRIVATE).getString("motorvm","");
        String motoram = getSharedPreferences("MOTORAM",MODE_PRIVATE).getString("motoram","");

        muc_data data1 = new muc_data("电机转速",motorspeed,"[-15000,15000]");
        muc_data data2 = new muc_data("电机转矩",motortorque,"[-5000,5000]");
        muc_data data3 = new muc_data("电机电流",totalcurrent,"1：正常 0：不正常");
        muc_data data4 = new muc_data("电机电压",totalvoltage,"1：正常 0：不正常");
        muc_data data5 = new muc_data("驱动电机温度",motortem,"[-40,215]");
        muc_data data6 = new muc_data("电机控制器输入电压",motorvm,"[0,1000]");
        muc_data data7 = new muc_data("控制器直流母线电流",motoram,"[-1000,1000]");

        mucData.add(data1);
        mucData.add(data2);
        mucData.add(data3);
        mucData.add(data4);
        mucData.add(data5);
        mucData.add(data6);
        mucData.add(data7);

        /**获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
        recyclerView.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new adapter_CurrentInfo(mucData); //获取适配器实例
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //调用系统横向分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));  //调用系统纵向分割线
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    private void initdata_VCU(){
        tv_header_centerText.setText("VCU当前参数");
        String vehiclestatus = getSharedPreferences("VEHICLESTATUS",MODE_PRIVATE).getString("vehiclestatus","");
        String chargingstate = getSharedPreferences("CHARGINGSTATE",MODE_PRIVATE).getString("chargingstate","");
        String model = getSharedPreferences("MODEL",MODE_PRIVATE).getString("model","");
        String speed = getSharedPreferences("SPEED",MODE_PRIVATE).getString("speed","");
        String mileage = getSharedPreferences("MILEAGE",MODE_PRIVATE).getString("mileage","");


        muc_data data1 = new muc_data("车辆状态",vehiclestatus,"[-400,400]");
        muc_data data2 = new muc_data("充电状态",chargingstate,"[0,2]");
        muc_data data3 = new muc_data("运行模式",model,"[0,15]");
        muc_data data4 = new muc_data("车速",speed,"[-50,150]");
        muc_data data5 = new muc_data("累计里程",mileage,"[0,131072]");

        mucData.add(data1);
        mucData.add(data2);
        mucData.add(data3);
        mucData.add(data4);
        mucData.add(data5);


        /**获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
        recyclerView.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new adapter_CurrentInfo(mucData); //获取适配器实例
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //调用系统横向分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));  //调用系统纵向分割线
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:
                finish();
                break;
        }
    }
}
