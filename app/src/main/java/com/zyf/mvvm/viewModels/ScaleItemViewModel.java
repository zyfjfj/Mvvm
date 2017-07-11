package com.zyf.mvvm.viewModels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.adapter.ScaleAdapter;
import com.zyf.mvvm.adapter.ScaleSubjectAdapter;
import com.zyf.mvvm.databinding.ActivityScaleSubjectBinding;
import com.zyf.mvvm.models.ScaleItem;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.net.RetrofitHelper;
import com.zyf.mvvm.views.FunctionActivity;
import com.zyf.mvvm.views.ScaleActivity;
import com.zyf.mvvm.views.ScaleSubjectActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/7/6.
 */

public class ScaleItemViewModel {
    public ScaleSubject mScaleSubject;
    public ScaleItem mScaleItem;
    public ActivityScaleSubjectBinding mActivityScaleSubjectBinding;

    public ScaleItemViewModel() {
    }

    public ScaleItemViewModel(ActivityScaleSubjectBinding binding) {
        mActivityScaleSubjectBinding = binding;
    }

    public void onItemClick(View view) {
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        Toast.makeText(view.getContext(), mScaleSubject.name, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(view.getContext(), ScaleSubjectActivity.class);
        view.getContext().startActivity(intent);
        Subscriber<List<ScaleItem>> subscriber = new Subscriber<List<ScaleItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ScaleSubject", "", e);
            }

            @Override
            public void onNext(List<ScaleItem> scaleItems) {
                List<ScaleItemViewModel> scaleItemViewModels = new ArrayList<>();
                for (ScaleItem scaleItem : scaleItems) {
                    ScaleItemViewModel model = new ScaleItemViewModel();
                    model.mScaleItem = scaleItem;
                    scaleItemViewModels.add(model);
                    for (ScaleItem.Answer answer : scaleItem.Answers) {
                        Log.i("ScaleSubject", answer.name);
                    }
                }
                SingleScaleViewModel.mScaleItems = scaleItemViewModels;
                SingleScaleViewModel.init();

            }
        };
        RetrofitHelper.getInstance().getItemAnswerByScaleId(subscriber, mScaleSubject.id);
    }
}
