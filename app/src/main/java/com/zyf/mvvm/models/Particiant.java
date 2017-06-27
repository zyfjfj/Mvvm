package com.zyf.mvvm.models;

import android.view.View;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by zyf on 2017/6/20.
 */

public class Particiant extends PageInfo{
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

    public void onItemClick(View view) {
        Toast.makeText(view.getContext(),Name, Toast.LENGTH_SHORT).show();
    }
}
