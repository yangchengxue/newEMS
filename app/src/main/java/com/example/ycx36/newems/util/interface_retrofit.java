package com.example.ycx36.newems.util;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface interface_retrofit {

    //查车况  http://120.79.62.86:10003/getCarAll
    @POST("/getCarAll")
    Observable<getCarAllBean> getCarAll(
            @Body requestGetCarAllBean requestGetCarAllBean
    );

    //注册接口。。。 http://120.79.62.86:10003/registerUser
    @POST("/registerUser")
    Observable<regesterUserBean> regesterUser(
            @Body requestRegisterUserBean requestRegisterUserBean
    );

    @GET("users/{name}/repos")
        Call<ResponseBody> searchRepoInfo(
                @Path("name") String name
    );
}
