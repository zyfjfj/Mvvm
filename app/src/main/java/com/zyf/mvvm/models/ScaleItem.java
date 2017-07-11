package com.zyf.mvvm.models;


import java.util.List;

/**
 * Created by zyf on 2017/7/7.
 */
public class ScaleItem{

    public String Name;
    public int Number;
    public int Id;
    public List<Answer> Answers;
    public class Answer {
        int id;
        int index;
        public String name;
        int score;
        String enable;
    }

}
