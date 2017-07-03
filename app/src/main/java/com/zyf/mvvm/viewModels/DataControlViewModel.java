package com.zyf.mvvm.viewModels;

import android.databinding.BaseObservable;
import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.models.AssessResult;
import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.net.AssessResultService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zyf on 2017/6/26.
 */

public class DataControlViewModel extends BaseObservable {
    public List<DataItemViewModel> dataItemViewModels=new ArrayList<DataItemViewModel>();

    public void initData(final Handler mHandler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalParameterApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AssessResultService service = retrofit.create(AssessResultService.class);
        AssessResult assessResult =new AssessResult();
        assessResult.pagesize=90;
        assessResult.curpage=1;
        Call<Result<DatasWithPageInfo>> repos = service.listRepos(assessResult);
        repos.enqueue(new Callback<Result<DatasWithPageInfo>>() {
            @Override
            public void onResponse(Call<Result<DatasWithPageInfo>> call, Response<Result<DatasWithPageInfo>> response) {
                try {
                    Result<DatasWithPageInfo> testResultRespond= response.body();
                    if (testResultRespond != null) {
                        for (AssessResult assessResult :testResultRespond.Data.Datamanagemodels) {
                            DataItemViewModel dateItem=new DataItemViewModel();
                            dateItem.assessResult = assessResult;
                            dataItemViewModels.add(dateItem);
                        }
                        mHandler.sendEmptyMessage(0);
                    }

                } catch (Exception e) {
                    Log.e("===", "return:" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Result<DatasWithPageInfo>> call, Throwable t) {
                Log.e("===", "失败");
            }
        });
    }

}
