package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.Result;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zyf on 2017/6/19.
 */

public interface ParInfoService {
    @POST("getparticipantinfos")
    Observable<Result<ParInfoWithPageInfo>> getpantinfos(@Body ParInfoWithPageInfo parInfoWithPageInfo);

    //增加参与者
    @PUT("participant")
    Observable<Result<Particiant>> addPantInfos(@Body Particiant particiant);

    @GET("participant/{keycode}")
    Observable<Result<List<Particiant>>> getPantinfo(@Path("keycode") String code);
}
