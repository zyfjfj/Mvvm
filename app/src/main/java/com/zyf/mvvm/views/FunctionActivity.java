package com.zyf.mvvm.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zyf.mvvm.R;
import com.zyf.mvvm.viewModels.FunctionViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        final int particpantId = bundle.getInt("particpantId");

        final FunctionViewModel functionViewModel=new FunctionViewModel(mHandler);
        GridView gridview = (GridView) findViewById(R.id.functionGridView);
        int length = functionViewModel.imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", functionViewModel.imageRes[i]);//添加图像资源的ID
            map.put("ItemText", functionViewModel.name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.activity_function_item,//item的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.img_shoukuan,R.id.txt_shoukuan});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (functionViewModel.name[position]) {
                    case "图像高阶测评":
                        functionViewModel.programControl(5,particpantId);
                        break;
                    case "图像认知测评":
                        functionViewModel.programControl(1,particpantId);
                        break;
                }

            }
        });

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    Toast.makeText(FunctionActivity.this,"设置成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(FunctionActivity.this,"设置失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
