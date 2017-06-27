package com.zyf.mvvm.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.zyf.mvvm.BR;
import com.zyf.mvvm.R;
import com.zyf.mvvm.adapter.DataAdapter;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.models.TestResult;
import com.zyf.mvvm.net.ParInfoService;
import com.zyf.mvvm.viewModels.DataControlViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataControlActivity extends AppCompatActivity {
    private DataControlViewModel dataControlViewModel;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_control);

        dataControlViewModel=new DataControlViewModel();
        dataControlViewModel.initData(mHandler);
        lv=(ListView)findViewById(R.id.dataControlListView);
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            DataAdapter<TestResult> adapter = new DataAdapter<>(DataControlActivity.this, R.layout.data_control_item,
                    dataControlViewModel.testResults, BR.testResult);
            lv.setAdapter(adapter);
        }
    };
}
