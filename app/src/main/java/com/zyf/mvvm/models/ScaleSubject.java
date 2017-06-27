package com.zyf.mvvm.models;

import java.util.Date;
import java.util.List;

/**
 * Created by zyf on 2017/6/19.
 */
public class ScaleSubject{
        public int id;
        public String name;
        public String code;
        public String describe;
        public String rule;
        public String guidance;
        public String criteria;
        public Date createtime;
        public String enable;

        @Override
        public String toString() {
                return name+":"+describe;
        }
}
