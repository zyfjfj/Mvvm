package com.zyf.mvvm.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityMainBinding;
import com.zyf.mvvm.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    //定义图标数组
    private int[] imageRes = {
            R.drawable.system,
            R.drawable.data,
            R.drawable.image,
            R.drawable.sacle
    };

    //定义图标下方的名称数组
    private String[] name = {
            "信息录入",
            "数据管理",
            "图像测评",
            "量表测评"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel();
        mainViewModel.firstName.set("zyf");
        mainViewModel.lastName.set("you");
        binding.setMainViewModel(mainViewModel);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        int length = imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.activity_function_item,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.img_shoukuan});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, name[position], Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                switch (name[position]) {
                    case "数据管理":
                        intent.setClass(MainActivity.this, DataControlActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case "量表测评":
                        mainViewModel.onClickFriend(view);
                        intent.setClass(MainActivity.this,ScaleActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case "图像测评":
                        break;
                    case "信息录入":
                        intent.setClass(MainActivity.this,ParInfoActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                }

            }
        });
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
