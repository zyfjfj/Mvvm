package com.zyf.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zyf.mvvm.R;

import com.zyf.mvvm.adapter.ScaleAdapter;
import com.zyf.mvvm.databinding.ActivityScaleSubjectBinding;
import com.zyf.mvvm.viewModels.ScaleItemViewModel;
import com.zyf.mvvm.viewModels.SingleScaleViewModel;

public class ScaleSubjectActivity extends AppCompatActivity {
    private ActivityScaleSubjectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_scale_subject);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        binding.recyclerView.setLayoutManager(layoutManager);
        SingleScaleViewModel itemViewModel=new SingleScaleViewModel(binding);
        binding.recyclerView.addItemDecoration(new ScaleAdapter.MyItemDecoration());
        binding.setScaleItem(itemViewModel);
    }

}
