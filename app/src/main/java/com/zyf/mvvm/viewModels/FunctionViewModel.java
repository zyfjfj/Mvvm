package com.zyf.mvvm.viewModels;

import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.R;
import com.zyf.mvvm.models.ProgramControl;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.net.ProgramControlService;
import com.zyf.mvvm.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by zyf on 2017/6/28.
 */

public class FunctionViewModel {
    final Handler handler;
    public FunctionViewModel(Handler handler) {
        this.handler = handler;
    }

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
    public void programControl( int functionId, int particpantId) {
        ProgramControl programControl=new ProgramControl();
        programControl.particpantid=particpantId;
        programControl.functionid=functionId;
        programControl.status=1;
        Subscriber<ProgramControl> subscriber = new Subscriber<ProgramControl>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(ProgramControl programControl) {
                if (programControl != null) {
                    Log.i(FunctionViewModel.class.getName(),"成功");
                    handler.sendEmptyMessage(1);
                }

            }
        };
        RetrofitHelper.getInstance().setProgramStatus(subscriber,programControl);
    }
}
