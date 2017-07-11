package com.zyf.mvvm.net;

import android.database.CursorJoiner;

import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleItem;
import com.zyf.mvvm.models.ScaleSubject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zyf on 2017/6/27.
 */

public interface ScaleSubjectService {
    @GET("scalesubject")
    Observable<Result<List<ScaleSubject>>> getScaleSubjects();

    @GET("scaleitem/{id}")
    Observable<Result<List<ScaleItem>>> getItemAnswerByScaleId(@Path("id") int id);
}
