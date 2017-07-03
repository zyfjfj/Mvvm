package com.zyf.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityInputInforBinding;
import com.zyf.mvvm.viewModels.ParticiantItemViewModel;

public class InputInforActivity extends AppCompatActivity {
    private ActivityInputInforBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_infor);
        ParticiantItemViewModel particiantItemViewModel = new ParticiantItemViewModel(this, binding);
    }

}
