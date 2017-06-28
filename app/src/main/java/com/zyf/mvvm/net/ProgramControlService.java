package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ProgramControl;
import com.zyf.mvvm.models.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

/**
 * Created by zyf on 2017/6/28.
 */

public interface ProgramControlService {
    @PUT("programControl")
    Call<Result<ProgramControl>> listRepos(@Body ProgramControl programControl);
}
