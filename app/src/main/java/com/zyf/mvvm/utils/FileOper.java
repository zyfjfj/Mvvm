package com.zyf.mvvm.utils;

import android.util.Base64;
import android.util.Log;

import com.zyf.mvvm.GlobalParameterApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by zyf on 2017/7/17.
 */

public class FileOper {
    public static void writeFileSdcard(String fileName, String content) {

        try {
            File file = new File(GlobalParameterApplication.file_path, fileName);
            OutputStream out = new FileOutputStream(file);
            out.write(content.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            Log.e("file",e.getMessage());
        }
    }

    public static String readFileSdcardFile(String fileName) {
        fileName= GlobalParameterApplication.file_path+"/"+fileName;
        String res = "";
        try {
            FileInputStream fin = new FileInputStream(fileName);
            InputStreamReader inputreader = new InputStreamReader(fin,"UTF-8");//设置流读取方式
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line=null;
            while (( line = buffreader.readLine()) != null) {
                res += line + "\n";//读取的文件内容
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将字符串转为图片
     * @param imgStr 图像字符串
     * @param imgFileName 文件名称
     * @return
     * @throws Exception
     */
    public static boolean generateImage(String imgFileName,String imgStr) {
        imgFileName= GlobalParameterApplication.file_path+"/"+imgFileName+".jpg";
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;

        try {
            // Base64解码
            byte[] b = Base64.decode(imgStr,Base64.DEFAULT);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFileName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
