package com.zyf.mvvm.viewModels;

import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.net.ParInfoService;

import java.util.ArrayList;
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
    public List<ParticiantItemViewModel> particiantItemViewModels=new ArrayList<ParticiantItemViewModel>();

    public void initData(final Handler mHandler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalParameterApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ParInfoService service = retrofit.create(ParInfoService.class);
        ParInfoWithPageInfo parInfoWithPageInfo =new ParInfoWithPageInfo();
        parInfoWithPageInfo.pagesize=90;
        parInfoWithPageInfo.curpage=1;
        Call<Result<ParInfoWithPageInfo>> repos = service.pantinfos(parInfoWithPageInfo);
        repos.enqueue(new Callback<Result<ParInfoWithPageInfo>>() {
            @Override
            public void onResponse(Call<Result<ParInfoWithPageInfo>> call, Response<Result<ParInfoWithPageInfo>> response) {
                try {
                    Result<ParInfoWithPageInfo> parInfoRespond= response.body();
                    if (parInfoRespond != null) {

                        for (Particiant parInfo:parInfoRespond.Data.Participants) {
                            ParticiantItemViewModel particintItem=new ParticiantItemViewModel();
                            particintItem.particiant=parInfo;
                            particiantItemViewModels.add(particintItem);
                        }

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
