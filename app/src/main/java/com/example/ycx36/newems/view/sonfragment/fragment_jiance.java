package com.example.ycx36.newems.view.sonfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.getCarInfo;
import com.example.ycx36.newems.view.activity.ExaminationActivity;
import com.example.ycx36.newems.view.activity.activity_CurrentInformation;
import com.github.lzyzsd.circleprogress.CircleProgress;


import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

//常规检测
public class fragment_jiance extends Fragment {

    @BindView(R.id.circle_progress) CircleProgress circle_progress;
    @BindView(R.id.text_tipStatus) TextView text_tipStatus;
    @BindView(R.id.progressBar11) ProgressBar progressBar11;
    @BindView(R.id.progressBar12) ProgressBar progressBar12;
    @BindView(R.id.progressBar13) ProgressBar progressBar13;
    @BindView(R.id.progressBar14) ProgressBar progressBar14;
    @BindView(R.id.d1) TextView d1;
    @BindView(R.id.d2) TextView d2;
    @BindView(R.id.d3) TextView d3;
    @BindView(R.id.d4) TextView d4;
    @BindView(R.id.d5) TextView d5;
    @BindView(R.id.d6) TextView d6;
    @BindView(R.id.d7) TextView d7;
    getCarInfo getCarInfo;
    int flag_ifSafety_MCU = 0;  //标志位  0为安全，1为不安全
    int flag_ifSafety_BMS = 0;  //标志位  0为安全，1为不安全
    int flag_ifSafety_VCU = 0;  //标志位  0为安全，1为不安全

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_jiance, container, false);
            ButterKnife.bind(this,view);
            getCarInfo = new getCarInfo(getActivity());
            SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("username", MODE_PRIVATE);
            String username = pref.getString("username", "");
            SharedPreferences pref2 = Objects.requireNonNull(getActivity()).getSharedPreferences("password", MODE_PRIVATE);
            String password = pref2.getString("password", "");
            getCarInfo.getCarAllData_save(username, password);
            startExamination();
        }
        return view;
    }

    int status = 0;
    int flag = 0,flag2 = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        public void handleMessage(Message msg){
            if (msg.what == 0x111){
                if (flag == 0){
                    flag = 1;
                    d1.setTextColor(getResources().getColor(R.color.gray3));
                    d2.setTextColor(getResources().getColor(R.color.gray3));
                    d4.setTextColor(getResources().getColor(R.color.gray3));
                }
                d1.setText("检测中…"+status+"%");
                d2.setText("检测中…"+status+"%");
                d4.setText("检测中…"+status+"%");
                circle_progress.setProgress(status);
                progressBar11.setProgress(status);
                progressBar12.setProgress(status);
//                progressBar13.setProgress(status);
                progressBar14.setProgress(status);
            }
            if (msg.what == 2){
                text_tipStatus.setText("检测完成");
                if (flag_ifSafety_BMS == 0){
                    d1.setText("安全（点击查看详情）");
                }else {
                    d1.setText("存在故障（点击查看详情）");
                }
                if (flag_ifSafety_MCU == 0) {
                    d2.setText("安全（点击查看详情）");
                }else {
                    d2.setText("存在故障（点击查看详情）");
                }
                if (flag_ifSafety_VCU == 0) {
                    d4.setText("安全（点击查看详情）");
                }else {
                    d4.setText("存在故障（点击查看详情）");
                }
                llflag = 1;
            }
        }
    };


    public void startExamination(){
        Check_MCU();   //直接开始
        Check_BMS();
        Check_VCU();
        new Thread(){
            public void run(){
                while (status<100){
                    status++;
                    //模拟耗时操作
                    try
                    {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x111);
                    if (status==100){
                        handler.sendEmptyMessage(2);
                    }
                }
            }
        }.start();
    }

    int llflag = 0;
    @OnClick({R.id.ll1,R.id.ll2,R.id.ll3,R.id.ll4,R.id.ll5,R.id.ll6,R.id.ll7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                if (llflag == 1){
                    Intent intent = new Intent(getActivity(), activity_CurrentInformation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("FromWhich", "1");
                    intent.putExtras(bundle);
                    startActivity(intent);
//                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("ExaminationFraction",MODE_PRIVATE).edit();
//                    editor.putInt("muc",5);
//                    editor.apply();
                }
                break;
            case R.id.ll2:   //mcu电机
                if (llflag == 1){
                    Intent intent = new Intent(getActivity(), activity_CurrentInformation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("FromWhich", "2");
                    intent.putExtras(bundle);
                    startActivity(intent);
//                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("ExaminationFraction",MODE_PRIVATE).edit();
//                    editor.putInt("muc",5);
//                    editor.apply();
                }
                break;
            case R.id.ll3:
                break;
            case R.id.ll4:
                if (llflag == 1){
                    Intent intent = new Intent(getActivity(), activity_CurrentInformation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("FromWhich", "4");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.ll5:
                break;
            case R.id.ll6:
                break;
            case R.id.ll7:
                break;
        }
    }


    public void Check_MCU(){
        String motorspeed = Objects.requireNonNull(getActivity()).getSharedPreferences("MOTORSPEED",MODE_PRIVATE).getString("motorspeed","");
        String motortorque = Objects.requireNonNull(getActivity()).getSharedPreferences("MOTOTTORQUE",MODE_PRIVATE).getString("motortorque","");
        String totalcurrent = Objects.requireNonNull(getActivity()).getSharedPreferences("TOTALCURRENT",MODE_PRIVATE).getString("totalcurrent","");
        String totalvoltage = Objects.requireNonNull(getActivity()).getSharedPreferences("TOTALVOLTAGE",MODE_PRIVATE).getString("totalvoltage","");
        String motortem = Objects.requireNonNull(getActivity()).getSharedPreferences("MOTORTEM",MODE_PRIVATE).getString("motortem",""); //驱动电机温度
        String motorvm = Objects.requireNonNull(getActivity()).getSharedPreferences("MOTORVM",MODE_PRIVATE).getString("motorvm","");  //电机控制器输入电压
        String motoram = Objects.requireNonNull(getActivity()).getSharedPreferences("MOTORAM",MODE_PRIVATE).getString("motoram","");  //电机控制器直流母线电流
        if (!motorspeed.equals("")){
            if (Double.parseDouble(motorspeed) >15000 || Double.parseDouble(motorspeed) < -15000){
                //电机转速异常
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!motortorque.equals("")){
            if (Double.parseDouble(motortorque) >5000 || Double.parseDouble(motortorque) < -5000){
                //电机扭矩异常
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!totalcurrent.equals("")){
            if (Double.parseDouble(totalcurrent) >1000 || Double.parseDouble(totalcurrent) < -1000){
                //电机电流异常
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!totalvoltage.equals("")){
            if (Double.parseDouble(totalvoltage) >1000 || Double.parseDouble(totalvoltage) < 0){
                //电机电压异常
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!motortem.equals("")){
            if (Double.parseDouble(motortem) >215 || Double.parseDouble(motortem) < -40){
                //驱动电机温度
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!motorvm.equals("")){
            if (Double.parseDouble(motorvm) >1000 || Double.parseDouble(motorvm) < 0){
                //电机控制器输入电压
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
        if (!motoram.equals("")){
            if (Double.parseDouble(motoram) >1000 || Double.parseDouble(motoram) < -1000){
                //电机控制器直流母线电流
                flag_ifSafety_MCU = 1;
            }
        }else {
            flag_ifSafety_MCU = 1;
        }
    }

    public void Check_BMS(){
        String voltage = Objects.requireNonNull(getActivity()).getSharedPreferences("VOLTAGE",MODE_PRIVATE).getString("voltage","");
        if (!voltage.equals("")){
            if (Double.parseDouble(voltage) >800 || Double.parseDouble(voltage) < 0){
                //总电压
                flag_ifSafety_BMS = 1;
            }
        }
        else {
            flag_ifSafety_BMS = 1;
        }
        String current = Objects.requireNonNull(getActivity()).getSharedPreferences("CURRENT",MODE_PRIVATE).getString("current","");
        if (!current.equals("")){
            if (Double.parseDouble(current) >500 || Double.parseDouble(current) < -500){
                //总电流
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String aftercurrent = Objects.requireNonNull(getActivity()).getSharedPreferences("AFTERCURRENT",MODE_PRIVATE).getString("aftercurrent","");
        if (!aftercurrent.equals("")){
            if (Double.parseDouble(aftercurrent) >100 || Double.parseDouble(aftercurrent) < 0){
                //剩余电流
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unitvm = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITVM",MODE_PRIVATE).getString("Unitvm","");
        if (!Unitvm.equals("")){
            if (Double.parseDouble(Unitvm) >6 || Double.parseDouble(Unitvm) < 0){
                //最高单体电压
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unitid = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITID",MODE_PRIVATE).getString("Unitid","");
        if (!Unitid.equals("")){
            if (Double.parseDouble(Unitid) >250 || Double.parseDouble(Unitid) < 0){
                //最低单体电压序号
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unitvmlow = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITVMLOW",MODE_PRIVATE).getString("Unitvmlow","");
        if (!Unitvmlow.equals("")){
            if (Double.parseDouble(Unitvmlow) >6 || Double.parseDouble(Unitvmlow) < 0){
                //最低单体电压
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unittemid = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITTEMID",MODE_PRIVATE).getString("Unittemid","");
        if (!Unittemid.equals("")){
//            if (Double.parseDouble(Unittemid) >250 || Double.parseDouble(Unittemid) < 0){
                //最高单体温度探针序号
                flag_ifSafety_BMS = 1;
//            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unittem = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITTEM",MODE_PRIVATE).getString("Unittem","");
        if (!Unittem.equals("")){
            if (Double.parseDouble(Unittem) >154 || Double.parseDouble(Unittem) < -50){
                //最高电池单体温度
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unitidlow = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITIDLOW",MODE_PRIVATE).getString("Unitidlow","");
        if (!Unitidlow.equals("")){
            try {} catch (Exception e){
                if (Double.parseDouble(Unitidlow) >250 || Double.parseDouble(Unitidlow) < 0){
                    //最低单体温度探针序号
                    flag_ifSafety_BMS = 1;
                }
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
        String Unittemlow = Objects.requireNonNull(getActivity()).getSharedPreferences("UNITTEMLOW",MODE_PRIVATE).getString("Unittemlow","");
        if (!Unittemlow.equals("")){
            if (Double.parseDouble(Unittemlow) >154 || Double.parseDouble(Unittemlow) < -50){
                //最低电池单体温度
                flag_ifSafety_BMS = 1;
            }
        }else {
            flag_ifSafety_BMS = 1;
        }
    }

    public void Check_VCU(){
        String vehiclestatus = Objects.requireNonNull(getActivity()).getSharedPreferences("VEHICLESTATUS",MODE_PRIVATE).getString("vehiclestatus","");
        if (!vehiclestatus.equals("")){
            if (Double.parseDouble(vehiclestatus) >400 || Double.parseDouble(vehiclestatus) < -400){
                //车辆状态
                flag_ifSafety_VCU = 1;
            }
        }else {
            flag_ifSafety_VCU = 1;
        }
        String chargingstate = Objects.requireNonNull(getActivity()).getSharedPreferences("CHARGINGSTATE",MODE_PRIVATE).getString("chargingstate","");
        if (!chargingstate.equals("")){
            if (Double.parseDouble(chargingstate) >2 || Double.parseDouble(chargingstate) < 0){
                //充电状态
                flag_ifSafety_VCU = 1;
            }
        }else {
            flag_ifSafety_VCU = 1;
        }
        String model = Objects.requireNonNull(getActivity()).getSharedPreferences("MODEL",MODE_PRIVATE).getString("model","");
        if (!model.equals("")){
            if (Double.parseDouble(model) >15 || Double.parseDouble(model) < 0){
                //运行模式
                flag_ifSafety_VCU = 1;
            }
        }else {
            flag_ifSafety_VCU = 1;
        }
        String speed = Objects.requireNonNull(getActivity()).getSharedPreferences("SPEED",MODE_PRIVATE).getString("speed","");
        if (!speed.equals("")){
            if (Double.parseDouble(speed) >150 || Double.parseDouble(speed) < -50){
                //车速
                flag_ifSafety_VCU = 1;
            }
        }else {
            flag_ifSafety_VCU = 1;
        }
        String mileage = Objects.requireNonNull(getActivity()).getSharedPreferences("MILEAGE",MODE_PRIVATE).getString("mileage","");
        if (!mileage.equals("")){
            if (Double.parseDouble(mileage) >131072 || Double.parseDouble(mileage) < 0){
                //累计里程
                flag_ifSafety_VCU = 1;
            }
        }else {
            flag_ifSafety_VCU = 1;
        }
    }

}
