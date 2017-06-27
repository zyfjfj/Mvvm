package com.zyf.mvvm.net;

import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleSubject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zyf on 2017/6/27.
 */

public interface ScaleSubjectService {
    @GET("scalesubject")
    Call<Result<List<ScaleSubject>>> listRepos();
}
