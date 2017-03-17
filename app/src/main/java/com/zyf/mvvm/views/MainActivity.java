package com.zyf.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zyf.mvvm.R;
import com.zyf.mvvm.viewModels.MainViewModel;
import com.zyf.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel();
        mainViewModel.firstName.set("zyf");
        mainViewModel.lastName.set("you");
        binding.setMainViewModel(mainViewModel);
        delay();
    }

    private void delay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainViewModel.lastName.set("Com");
            }
        }, 2000);
    }
}
