package com.zyf.mvvm.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/3/17.
 */

public interface LoginService {
    @GET("login/{name}")
    Call<String> listRepos(@Path("name") String name);
}
