package com.zyf.mvvm.viewModels;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.net.RetrofitHelper;
import com.zyf.mvvm.net.ScaleSubjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MainViewModel extends BaseObservable {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();


    public void onClickFriend(View view) {
        Subscriber<List<ScaleSubject>> subscriber = new Subscriber<List<ScaleSubject>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ScaleSubject> scaleSubjects) {
                for (ScaleSubject scale : scaleSubjects) {
                    Log.i("===", "scale:" + scale.name);
                }
            }
        };
        RetrofitHelper.getInstance().getScaleSubjects(subscriber);
    }
}
