package com.zyf.mvvm.viewModels;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.net.ParInfoService;
import com.zyf.mvvm.net.ScaleSubjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MainViewModel extends BaseObservable {
    public final ObservableField<String> firstName=new ObservableField<>();
    public final ObservableField<String> lastName=new ObservableField<>();


    public void onClickFriend(View view) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GlobalParameterApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScaleSubjectService service=retrofit.create(ScaleSubjectService.class);
        Call<Result<List<ScaleSubject>>> repos=service.listRepos();
        repos.enqueue(new Callback<Result<List<ScaleSubject>>>() {
            @Override
            public void onResponse(Call<Result<List<ScaleSubject>>> call, Response<Result<List<ScaleSubject>>> response) {
                try {
                    Result<List<ScaleSubject>> scaleSubjectRespond=response.body();
                    Log.i("===","return:"+scaleSubjectRespond.toString());

                }catch (Exception e){
                    Log.e("===","return:"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Result<List<ScaleSubject>>> call, Throwable t) {
                Log.e("===","失败");
            }
        });
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
    }
}
