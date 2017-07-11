package com.zyf.mvvm.viewModels;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zyf.mvvm.adapter.ScaleAdapter;
import com.zyf.mvvm.databinding.ActivityScaleBinding;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/7/6.
 */

public class ScaleViewModel {
    public void init(final ActivityScaleBinding binding) {
        Subscriber<List<ScaleSubject>> subscriber = new Subscriber<List<ScaleSubject>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ScaleSubject","",e);
            }

            @Override
            public void onNext(List<ScaleSubject> scaleSubjects) {
                List<ScaleItemViewModel> scaleItemViewModels = new ArrayList<>();
                for (ScaleSubject scale : scaleSubjects) {
                    ScaleItemViewModel itemViewModel = new ScaleItemViewModel();
                    itemViewModel.mScaleSubject = scale;
                    scaleItemViewModels.add(itemViewModel);
                }
                binding.recyclerView.setAdapter(new ScaleAdapter(scaleItemViewModels));
            }
        };
        RetrofitHelper.getInstance().getScaleSubjects(subscriber);
    }

}
