package com.zyf.mvvm.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zyf.mvvm.R;
import com.zyf.mvvm.adapter.ScaleAdapter;
import com.zyf.mvvm.databinding.ActivityScaleBinding;
import com.zyf.mvvm.viewModels.ScaleViewModel;

public class ScaleActivity extends AppCompatActivity {
    private ActivityScaleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_scale);
/*        setContentView(R.layout.activity_scale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
//        LinearLayoutManager layoutManager = new LinearLayoutManager(
//                this, LinearLayoutManager.VERTICAL, false);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //调用以下方法让RecyclerView的第一个条目仅为1列
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，再此也就是3
                //而如果不是0，则说明不是Header，就占用1列即可
                return 1;
            }
        });
        binding.recyclerView.setLayoutManager(layoutManager);
        ScaleViewModel viewModel=new ScaleViewModel();
        binding.setScale(viewModel);
        //binding.recyclerView.addItemDecoration(new ScaleAdapter.DividerItemDecoration(this, ScaleAdapter.DividerItemDecoration.VERTICAL_LIST));
        //binding.recyclerView.addItemDecoration(new ScaleAdapter.GridSpacingItemDecoration(3,300));
        binding.recyclerView.addItemDecoration(new ScaleAdapter.MyItemDecoration());
        viewModel.init(binding);
    }

}
