package com.zyf.mvvm.viewModels;

import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.R;
import com.zyf.mvvm.models.ProgramControl;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.net.ProgramControlService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zyf on 2017/6/28.
 */

public class FunctionViewModel {
    //定义图标数组
    public int[] imageRes = {
            R.drawable.txrz,
            R.drawable.gj
    };

    //定义图标下方的名称数组
    public String[] name = {
            "图像认知测评",
            "图像高阶测评"
    };

    /**
     * 发送控制指令
     * @param functionId
     * @param particpantId
     */
    public void programControl(final Handler handler, int functionId, int particpantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalParameterApplication.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProgramControlService service = retrofit.create(ProgramControlService.class);
        ProgramControl programControl=new ProgramControl();
        programControl.particpantid=particpantId;
        programControl.functionid=functionId;
        programControl.status=1;
        Call<Result<ProgramControl>> repos = service.listRepos(programControl);
        repos.enqueue(new Callback<Result<ProgramControl>>() {
            @Override
            public void onResponse(Call<Result<ProgramControl>> call, Response<Result<ProgramControl>> response) {
                try {
                    Result<ProgramControl> programControlResultRespond= response.body();
                    if (programControlResultRespond != null) {
                        ProgramControl pControl=new ProgramControl();
                        pControl=programControlResultRespond.data;
                        handler.sendEmptyMessage(1);
                    }

                } catch (Exception e) {
                    handler.sendEmptyMessage(2);
                    Log.e("===", "return:" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Result<ProgramControl>> call, Throwable t) {
                Log.e("===", "失败");
            }
        });
    }
}
