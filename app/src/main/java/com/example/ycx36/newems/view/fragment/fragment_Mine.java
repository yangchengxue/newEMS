package com.example.ycx36.newems.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.service.autofill.SaveCallback;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.userInfoBean;
import com.example.ycx36.newems.view.activity.MainActivity;
import com.example.ycx36.newems.view.activity.activity_SignIn;
import com.example.ycx36.newems.view.activity.activity_aboutUs;
import com.example.ycx36.newems.view.activity.activity_alarmSet;
import com.example.ycx36.newems.view.activity.activity_carSet;
import com.example.ycx36.newems.view.activity.activity_changeInfo;
import com.example.ycx36.newems.view.activity.activity_phoneSet;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.FileNotFoundException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class fragment_Mine extends Fragment {

    @BindView(R.id.userDrawee) SimpleDraweeView userDrawee;
    @BindView(R.id.currentUsername) TextView currentUsername;
    @BindView(R.id.carVINTitle) TextView carVINTitle;
    @BindView(R.id.carVINValue) TextView carVINValue;
    @BindView(R.id.bt_signIn_Out) Button bt_signIn_Out;

    private View view;

    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
            ButterKnife.bind(this,view);

        }
        return view;
    }

    @OnClick({R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4,R.id.RL_5,R.id.RL_6,R.id.userDrawee,R.id.bt_signIn_Out})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.RL_1:
                if (!currentUsername.getText().toString().equals("未登录")){
                    startActivity(new Intent(getActivity(),activity_changeInfo.class));
                }else {
                    startActivity(new Intent(getActivity(),activity_SignIn.class));
                }
                break;
            case R.id.RL_2:
                startActivity(new Intent(getActivity(), activity_carSet.class));
                break;
            case R.id.RL_3:
                startActivity(new Intent(getActivity(), activity_alarmSet.class));
                break;
            case R.id.RL_4:
                startActivity(new Intent(getActivity(), activity_phoneSet.class));
                break;
            case R.id.RL_5:
                break;
            case R.id.RL_6:
                startActivity(new Intent(getActivity(), activity_aboutUs.class));
                break;
            case R.id.userDrawee:
                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/png");
                startActivityForResult(intentToPickPic, 1);
                break;
            case R.id.bt_signIn_Out:
                if (bt_signIn_Out.getText() == "退出登录"){
                    SignOut();
                }else{
                    startActivity(new Intent(getActivity(),activity_SignIn.class));
                }
                break;
        }
    }


    public void saveUserData(String NAME,String name,String data){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(NAME,MODE_PRIVATE).edit();
        editor.putString(name,data);
        editor.apply();
    }

    /**退出登录*/
    public void SignOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());//创建一个提示对话框的构造者
        builder.setTitle("提示");  //设置对话框的标题
        builder.setMessage("是否登出当前用户？");  //设置提示信息
        builder.setIcon(R.mipmap.ic_launcher);  //设置对话框的图标

        //设置正面的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出登录的逻辑
                currentUsername.setText("未登录");
                bt_signIn_Out.setText("未登录");
                //存储信息
                saveUserData("username","username","");
                saveUserData("password","password","");
                saveUserData("UID", "uid", ""); //设备车VIN码（唯一号）
                saveUserData("UPDATETIME", "updatetime", ""); //更新时间
                saveUserData("TOTALCURRENT", "totalcurrent", ""); //电流状态 1：正常 0：不正常
                saveUserData("TOTALVOLTAGE", "totalvoltage", ""); //电压状态 1：正常 0：不正常
                saveUserData("VEHICLESTATUS", "vehiclestatus", ""); //车辆状态
                saveUserData("CHARGINGSTATE", "chargingstate", ""); //充电状态
                saveUserData("MODEL", "model", ""); //运行模式
                saveUserData("SPEED", "speed", ""); //车速
                saveUserData("MILEAGE", "mileage", ""); //累计里程
                saveUserData("VOLTAGE", "voltage", ""); //总电压
                saveUserData("CURRENT", "current", ""); //总电流
                saveUserData("AFTERCURRENT", "aftercurrent", ""); //剩余电流
                saveUserData("QID", "qid",""); //驱动电机序号
                saveUserData("TEMPERATURE", "temperature", ""); //驱动电机控制器温度
                saveUserData("MOTORSPEED", "motorspeed", "");  //驱动电机转速
                saveUserData("MOTOTTORQUE", "motortorque", "");  //驱动电机转矩
                saveUserData("MOTORTEM", "motortem", ""); //驱动电机温度
                saveUserData("MOTORVM", "motorvm", ""); //电机控制器输入电压
                saveUserData("MOTORAM", "motoram", ""); //电机控制器直流母线电流
                saveUserData("LONGITUDE", "longitude", ""); //经度
                saveUserData("LATITUDE", "latitude", ""); //纬度
                saveUserData("UNITVM", "Unitvm", ""); //最高单体电压
                saveUserData("UNITID", "Unitid", ""); //最低单体电压序号
                saveUserData("UNITVMLOW", "Unitvmlow", ""); //最低单体电压
                saveUserData("UNITTEMID", "Unittemid", ""); //最高单体温度探针序号
                saveUserData("UNITTEM", "Unittem", ""); //最高电池单体温度
                saveUserData("UNITIDLOW", "Unitidlow", ""); //最低单体温度探针序号
                saveUserData("UNITTEMLOW", "Unittemlow", ""); //最低电池单体温度
                carVINTitle.setVisibility(View.GONE);
                carVINValue.setVisibility(View.GONE);
            }
        });
        //设置反面的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //设置中立的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setNeutralButton("隐藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    private Uri imageUri;
    //当点击某涨照片完成时会回调到onActivityResult 在这里处理照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    // 获取图片
                    try {
                        //该uri是上一个Activity返回的
                        imageUri = data.getData();
                        if (imageUri != null) {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将相对路径转化为绝对路径
     */
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onResume() {
        super.onResume();
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("username",MODE_PRIVATE);
        String value = pref.getString("username","");
        if (!value.equals("")){
            currentUsername.setText(value);
            bt_signIn_Out.setText("退出登录");
            SharedPreferences pref1 = Objects.requireNonNull(getActivity()).getSharedPreferences("UID",MODE_PRIVATE);
            String vin = pref1.getString("uid","");
            carVINValue.setText(vin);
            carVINTitle.setVisibility(View.VISIBLE);
            carVINValue.setVisibility(View.VISIBLE);
        }
    }

}
