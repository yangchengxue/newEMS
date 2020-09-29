package com.example.ycx36.newems.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.view.fragment.fragment_CarStatus;
import com.example.ycx36.newems.view.fragment.fragment_Discover;
import com.example.ycx36.newems.view.fragment.fragment_Examination;
import com.example.ycx36.newems.view.fragment.fragment_Mine;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    private fragment_Examination examination = new fragment_Examination();
    private fragment_CarStatus carStatus = new fragment_CarStatus();
    private fragment_Discover discover = new fragment_Discover();
    private fragment_Mine mine = new fragment_Mine();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(fragmentManager,examination);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(fragmentManager,carStatus);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(fragmentManager,discover);
                    return true;
                case R.id.navigation4:
                    replaceFragment(fragmentManager,mine);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        replaceFragment(fragmentManager,examination);
        requestPermissions();

//        //测试okhttp
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://wwww.baidu.com")
//                .build();
//        final Call call =  client.newCall(request);
//        //异步请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("请求 异步： "," 出现错误 :" + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("请求 异步： "," " + response.body().toString());
//            }
//        });
//
//        //同步请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = call.execute();
//                    Log.d("请求 同步： "," " + response.body().toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        /**创建Retrofit对象*/
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        /**动态生成一个代理对象*/
//        interface_retrofit githubService = retrofit.create(interface_retrofit.class);
//        /**生成OkhttpCall的代理对象*/
//        final Call<ResponseBody> call = githubService.searchRepoInfo("changmu175");


        //同步请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response<ResponseBody> response = call.execute();
//                    Log.d("同步请求： "," " + response.body().toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        //异步请求方式
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //请求成功回调
//                Log.d("异步请求： "," " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                //请求与失败回调
//            }
//        });


    }

    /**替换fragment*/
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainFragment_layout, fragment);
            transaction.commit();
    }


    private void requestPermissions(){

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()){  //申请的集合不为空时，表示有需要申请的权限
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),1);
        }else { //所有的权限都已经授权过了

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1://获取多个权限
                if (grantResults.length > 0){ //安全写法，如果小于0，肯定会出错了
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED){ //如果权限被拒绝
                            String s = permissions[i];
                            Toast.makeText(this,s+"权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }else{ //授权成功了
                            //do Something
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onResume() {
        super.onResume();
        // 目标对象
        IUserDao target = new UserDao();
        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();

        proxy.save();
        proxy.end();
    }

}

/**接口*/
interface IUserDao {
    void save();
    void end();
}
/**实现类*/
class UserDao implements IUserDao {
    @Override
    public void save() {
        Log.d("hahaha","----已经保存数据!----");
    }

    @Override
    public void end() {
        Log.d("hahaha","----已经结束!----");
    }
}
/**代理类*/
class ProxyFactory {
    //维护一个目标对象
    private Object target;
    //构造函数
    public ProxyFactory(Object target){
        this.target=target;
    }

    //获取代理实例
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.d("hahaha","开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        Log.d("hahaha","提交事务2");
                        return returnValue;
                    }
                });
    }
}
