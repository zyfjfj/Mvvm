package com.zyf.mvvm.viewModels;

import com.zyf.mvvm.adapter.ScaleSubjectAdapter;
import com.zyf.mvvm.databinding.ActivityScaleSubjectBinding;
import com.zyf.mvvm.models.ScaleItem;

import java.util.List;

/**
 * Created by zyf on 2017/7/10.
 */

public class SingleScaleViewModel {
    public static List<ScaleItemViewModel> mScaleItems;
    public static ActivityScaleSubjectBinding mActivityScaleSubjectBinding;

    public SingleScaleViewModel(ActivityScaleSubjectBinding mActivityScaleSubjectBinding) {
        this.mActivityScaleSubjectBinding = mActivityScaleSubjectBinding;
    }
    public static void  init(){
        mActivityScaleSubjectBinding.recyclerView.setAdapter(new ScaleSubjectAdapter(mScaleItems));
    }

}
