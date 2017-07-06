package com.zyf.mvvm.viewModels;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.views.FunctionActivity;

/**
 * Created by zyf on 2017/7/6.
 */

public class ScaleItemViewModel {
    public ScaleSubject mScaleSubject;


    public void onItemClick(View view) {
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        Toast.makeText(view.getContext(), mScaleSubject.name, Toast.LENGTH_LONG).show();
    }
}
