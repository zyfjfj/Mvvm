package com.zyf.mvvm;

import android.app.Application;
import android.os.Environment;

import com.zyf.mvvm.utils.FileOper;

/**
 * Created by zyf on 2017/6/27.
 */

public class GlobalParameterApplication extends Application {
    public static String BaseUrl="http://192.168.0.116:9999/";
    public static String file_path = Environment.getExternalStorageDirectory() + "/ImageData";


    @Override
    public void onCreate() {
        super.onCreate();
        FileOper.makeRootDirectory(file_path);
    }
}
