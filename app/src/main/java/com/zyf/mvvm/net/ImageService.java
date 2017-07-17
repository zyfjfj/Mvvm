package com.zyf.mvvm.net;

import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.Sence;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by zyf on 2017/7/14.
 */

public interface ImageService {
    @PUT("scene")
    Observable<Result<List<Sence>>> getImages(@Body Sence.Ids ids);
}
