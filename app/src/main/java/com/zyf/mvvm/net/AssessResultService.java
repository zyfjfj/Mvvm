package com.zyf.mvvm.net;

import com.zyf.mvvm.models.AssessResult;
import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.models.Result;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zyf on 2017/6/27.
 */

public interface AssessResultService {
    @POST("getdatacontroller")
    Observable<Result<DatasWithPageInfo>> getAssessResults(@Body AssessResult assessResult);
}
