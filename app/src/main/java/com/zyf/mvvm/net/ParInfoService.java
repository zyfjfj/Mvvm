package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.viewModels.ParticiantItemViewModel;
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
    Call<Result<ParInfoWithPageInfo>> pantinfos(@Body ParticiantItemViewModel particiantItemViewModel);

    //增加参与者
    @PUT("participant")
    Call<Result<ParticiantItemViewModel>> addPantInfos(@Body ParticiantItemViewModel particiantItemViewModel);
}
