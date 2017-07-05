package com.zyf.mvvm.net;

import com.zyf.mvvm.models.ProgramControl;
import com.zyf.mvvm.models.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by zyf on 2017/6/28.
 */

public interface ProgramControlService {
    @PUT("programControl")
    Observable<Result<ProgramControl>> setProgramStatus(@Body ProgramControl programControl);
}
