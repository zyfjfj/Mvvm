package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.viewModels.ParticiantItemViewModel;
import com.zyf.mvvm.models.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zyf on 2017/6/19.
 */

public interface ParInfoService {
    @POST("getparticipantinfos")
    Call<Result<ParInfoWithPageInfo>> listRepos(@Body ParticiantItemViewModel particiantItemViewModel);
}
