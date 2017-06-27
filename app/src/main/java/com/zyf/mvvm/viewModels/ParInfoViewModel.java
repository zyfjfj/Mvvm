package com.zyf.mvvm.viewModels;

import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.TestResult;
import com.zyf.mvvm.net.ParInfoService;
import com.zyf.mvvm.net.TestResultService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zyf on 2017/6/27.
 */

public class ParInfoViewModel {
    public List<Particiant> particiants;

    public void initData(final Handler mHandler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalParameterApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ParInfoService service = retrofit.create(ParInfoService.class);
        Particiant particiant=new Particiant();
        particiant.pagesize=90;
        particiant.curpage=1;
        Call<Result<ParInfoWithPageInfo>> repos = service.listRepos(particiant);
        repos.enqueue(new Callback<Result<ParInfoWithPageInfo>>() {
            @Override
            public void onResponse(Call<Result<ParInfoWithPageInfo>> call, Response<Result<ParInfoWithPageInfo>> response) {
                try {
                    Result<ParInfoWithPageInfo> parInfoRespond= response.body();
                    if (parInfoRespond != null) {

                        particiants=parInfoRespond.getData().Participants;
                        mHandler.sendEmptyMessage(0);
                    }

                } catch (Exception e) {
                    Log.e("===", "return:" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Result<ParInfoWithPageInfo>> call, Throwable t) {
                Log.e("===", "失败");
            }
        });
    }
}