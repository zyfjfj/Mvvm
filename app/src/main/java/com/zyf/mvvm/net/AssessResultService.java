package com.zyf.mvvm.net;

import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.AssessResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zyf on 2017/6/27.
 */

public interface AssessResultService {
    @POST("getdatacontroller")
    Call<Result<DatasWithPageInfo>> listRepos(@Body AssessResult assessResult);
}
