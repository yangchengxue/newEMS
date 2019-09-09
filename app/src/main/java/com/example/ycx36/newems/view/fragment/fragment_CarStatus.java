package com.example.ycx36.newems.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ycx36.newems.R;
import com.example.ycx36.newems.view.activity.activity_Register;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class fragment_CarStatus extends Fragment {

    @BindView(R.id.shitu2) ImageView shitu2;
    @BindView(R.id.shitu1) ImageView shitu1;

    private View view;

    // 当前显示的bitmap对象
    private static Bitmap bitmap;
    // 图片容器
    private ImageView imageView;
    // 开始按下位置
    private int startX;
    // 当前位置
    private int currentX;
    // 当前图片的编号
    private int scrNum;
    // 图片的总数
    private static int maxNum = 52;
    // 资源图片集合
    private int[] srcs = new int[] { R.drawable.p1, R.drawable.p2,
            R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10,
            R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18,
            R.drawable.p19, R.drawable.p20, R.drawable.p21, R.drawable.p22,
            R.drawable.p23, R.drawable.p24, R.drawable.p25, R.drawable.p26,
            R.drawable.p27, R.drawable.p28, R.drawable.p29, R.drawable.p30,
            R.drawable.p31, R.drawable.p32, R.drawable.p33, R.drawable.p34,
            R.drawable.p35, R.drawable.p36, R.drawable.p37, R.drawable.p38,
            R.drawable.p39, R.drawable.p40, R.drawable.p41, R.drawable.p42,
            R.drawable.p43, R.drawable.p44, R.drawable.p45, R.drawable.p46,
            R.drawable.p47, R.drawable.p48, R.drawable.p49, R.drawable.p50,
            R.drawable.p51, R.drawable.p52 };

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_carstatus, container, false);
            ButterKnife.bind(this,view);
            imageView = view.findViewById(R.id.shitu1);
            // 初始化当前显示图片编号
            scrNum = 1;

            imageView.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = (int) event.getX();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            currentX = (int) event.getX();
                            // 判断手势滑动方向，并切换图片
                            if (currentX - startX > 10) {
                                modifySrcR();
                            } else if (currentX - startX < -10) {
                                modifySrcL();
                            }
                            // 重置起始位置
                            startX = (int) event.getX();

                            break;

                    }

                    return true;
                }

            });
        }
        return view;
    }

    // 向右滑动修改资源
    private void modifySrcR() {

        if (scrNum > maxNum) {
            scrNum = 1;
        }

        if (scrNum > 0) {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    srcs[scrNum - 1]);
            imageView.setImageBitmap(bitmap);
            scrNum++;
        }

    }

    // 向左滑动修改资源
    private void modifySrcL() {

        if (scrNum <= 0) {
            scrNum = maxNum;
        }

        if (scrNum <= maxNum) {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    srcs[scrNum - 1]);
            imageView.setImageBitmap(bitmap);
            scrNum--;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    //在这里可以进行UI操作
                    if (flag == 0){
                        shitu2.setVisibility(View.VISIBLE);
                        shitu1.setVisibility(View.GONE);
                        flag = 1;
                        Log.d("123123414    `1","dasfg");
                    } else if (flag == 1){
                        shitu2.setVisibility(View.GONE);
                        shitu1.setVisibility(View.VISIBLE);
                        flag = 0;
                        Log.d("123123414    `2","dasfg");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    int flag = 0;
    @OnClick({R.id.bt_chang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_chang:
                Log.d("123123414    `3","dasfg");
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                break;
        }
    }

}
