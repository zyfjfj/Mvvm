package com.zyf.mvvm.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zyf.mvvm.BR;
import com.zyf.mvvm.R;
import com.zyf.mvvm.adapter.DataAdapter;
import com.zyf.mvvm.models.PageInfo;
import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.viewModels.ParInfoViewModel;

public class ParInfoActivity extends AppCompatActivity {
    private ParInfoViewModel parInfoViewModel;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_info);

        parInfoViewModel=new ParInfoViewModel();
        lv=(ListView)findViewById(R.id.parInfoListView);
        parInfoViewModel.initData(mHandler);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            DataAdapter<Particiant> adapter = new DataAdapter<>(ParInfoActivity.this, R.layout.par_info_item,
                    parInfoViewModel.particiants, BR.particiant);
            lv.setAdapter(adapter);
        }
    };

}
