package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by zyf on 2017/6/19.
 */

public interface ParInfoService {
    @POST("getparticipantinfos")
    Call<Result<ParInfoWithPageInfo>> pantinfos(@Body ParInfoWithPageInfo parInfoWithPageInfo);

    //增加参与者
    @PUT("participant")
    Call<Result<Particiant>> addPantInfos(@Body Particiant particiant);
}
