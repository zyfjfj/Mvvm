package com.zyf.mvvm.viewModels;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.models.PageInfo;
import com.zyf.mvvm.views.DataControlActivity;
import com.zyf.mvvm.views.FunctionActivity;
import com.zyf.mvvm.views.MainActivity;
import com.zyf.mvvm.views.ParInfoActivity;

import java.util.Date;

/**
 * Created by zyf on 2017/6/20.
 */

public class ParticiantItemViewModel extends PageInfo {
    public int Id ;
    public String Name ;
    public int Sex ;
    public int Age ;
    public int EducationId ;
    public String Phone ;
    public int WorkId ;
    public int MaritalStatus ;
    public String KeyCode ;
    public String IsDose;
    public Date CreateTime ;
    public Date Birthday ;
    public String Birthcity ;
    public String Currentcity ;
    public String IsLeft ;
    public String Dep;
    public String Enable ;
    long[] mHits = new long[2];        //存储时间的数组


    public void onItemClick(View view) {
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //Toast.makeText(view.getContext(),""+Id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            //传递name参数为tinyphp
            bundle.putInt("particpantId", Id);
            intent.putExtras(bundle);
            intent.setClass(view.getContext(), FunctionActivity.class);
            view.getContext().startActivity(intent);
        }
    }
}
