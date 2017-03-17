package com.zyf.mvvm.viewModels;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.net.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MainViewModel extends BaseObservable {
    public final ObservableField<String> firstName=new ObservableField<>();
    public final ObservableField<String> lastName=new ObservableField<>();


    public void onClickFriend(View view) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        LoginService service=retrofit.create(LoginService.class);
        Call<String> repos=service.listRepos("yuo");
        repos.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.i("===","return:"+response.body().toString());

                }catch (Exception e){
                    Log.e("===","return:"+e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("===","失败");
            }
        });
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
    }
}
