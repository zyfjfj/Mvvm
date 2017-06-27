package com.zyf.mvvm.models;

import android.view.View;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by zyf on 2017/6/26.
 */

public class TestResult extends PageInfo {
    public int Id ;
    public int Partid ;
    public int Subjectid ;
    public int Recordid;
    public String score ;
    public String Describe ;
    public String Fileurl ;
    public byte[] File ;
    public String Filestr ;
    public String createtimeStr ;
    public String createtime ;
    public String  createtimeq;
    public String  createtimez;
    public String Dep ;
    public int Sex;
    public String Birthday;
    public String BirthdayStr;
    public String Project ;
    public String projectname ;
    public String name;
    public String Enable;

    public void onItemClick(View view) {
        Toast.makeText(view.getContext(),name, Toast.LENGTH_SHORT).show();
    }

}
