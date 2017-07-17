package com.zyf.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityImageBinding;
import com.zyf.mvvm.viewModels.ImageViewModel;

import static android.R.style.Theme_Black_NoTitleBar_Fullscreen;
import static android.R.style.Theme_NoTitleBar_Fullscreen;

public class ImageActivity extends AppCompatActivity {
    ActivityImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(Theme_NoTitleBar_Fullscreen);
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_image);
        ImageViewModel imageViewModel=new ImageViewModel(binding);
        imageViewModel.init();
        imageViewModel.setImageUrl("1111T1-1.72");
        binding.setImageViewModel(imageViewModel);
    }

}
