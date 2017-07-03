package com.zyf.mvvm.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.zyf.mvvm.BR;
import com.zyf.mvvm.R;
import com.zyf.mvvm.adapter.DataAdapter;
import com.zyf.mvvm.viewModels.ParInfoViewModel;
import com.zyf.mvvm.viewModels.ParticiantItemViewModel;

public class ParInfoActivity extends AppCompatActivity {
    private ParInfoViewModel parInfoViewModel;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), InputInforActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        parInfoViewModel = new ParInfoViewModel();
        lv = (ListView) findViewById(R.id.parInfoListView);
        (new Thread(){
            @Override
            public void run() {
                parInfoViewModel.initData(mHandler);
            }
        }).start();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            DataAdapter<ParticiantItemViewModel> adapter = new DataAdapter<>(ParInfoActivity.this, R.layout.par_info_item,
                    parInfoViewModel.particiantItemViewModels, BR.particiantItemViewModel);
            lv.setAdapter(adapter);
        }
    };

}
