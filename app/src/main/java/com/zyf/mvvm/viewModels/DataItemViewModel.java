package com.zyf.mvvm.viewModels;

import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.models.AssessResult;

/**
 * Created by zyf on 2017/7/3.
 */

public class DataItemViewModel {
    public AssessResult assessResult;

    public void onItemClick(View view) {
        Toast.makeText(view.getContext(), assessResult.name, Toast.LENGTH_SHORT).show();
    }
}
