package com.zyf.mvvm.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.zyf.mvvm.BR;
import com.zyf.mvvm.R;
import com.zyf.mvvm.adapter.DataAdapter;
import com.zyf.mvvm.viewModels.DataControlViewModel;
import com.zyf.mvvm.viewModels.DataItemViewModel;

public class DataControlActivity extends AppCompatActivity {
    private DataControlViewModel dataControlViewModel;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataControlViewModel=new DataControlViewModel();
        dataControlViewModel.initData(mHandler);
        lv=(ListView)findViewById(R.id.dataControlListView);
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            DataAdapter<DataItemViewModel> adapter = new DataAdapter<>(DataControlActivity.this, R.layout.data_control_item,
                    dataControlViewModel.dataItemViewModels, BR.dataItem);
            lv.setAdapter(adapter);
        }
    };
}
