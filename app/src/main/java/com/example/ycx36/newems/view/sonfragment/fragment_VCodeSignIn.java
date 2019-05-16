package com.example.ycx36.newems.view.sonfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ycx36.newems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class fragment_VCodeSignIn extends Fragment {

    @BindView(R.id.at_userNumber) AutoCompleteTextView at_userNumber;
    @BindView(R.id.at_VCode) EditText at_VCode;

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_vcode, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }


    @OnClick({R.id.bt_sign,R.id.getVcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign:
                login();
                break;
            case R.id.getVcode:

                break;
        }
    }


    public void login(){

    }
}
