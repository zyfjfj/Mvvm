package com.zyf.mvvm.viewModels;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityInputInforBinding;
import com.zyf.mvvm.databinding.ContentInputInforBinding;
import com.zyf.mvvm.models.PageInfo;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.net.ParInfoService;
import com.zyf.mvvm.views.DataControlActivity;
import com.zyf.mvvm.views.FunctionActivity;
import com.zyf.mvvm.views.InputInforActivity;
import com.zyf.mvvm.views.MainActivity;
import com.zyf.mvvm.views.ParInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zyf on 2017/6/20.
 */

public class ParticiantItemViewModel extends PageInfo {
    public int Id;
    public String Name;
    public int Sex;
    public int Age;
    public String AgeStr;
    public int EducationId;
    public String Phone;
    public int WorkId;
    public int MaritalStatus;
    public String KeyCode;
    public String IsDose;
    public Date CreateTime;
    public Date Birthday;
    public String BirthdayStr;
    public String Birthcity;
    public ObservableField<String> keyCodeError=new ObservableField<>();
    public ObservableField<String> nameError=new ObservableField<>();
    public String getBirthdayStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Birthday);
    }

    public String getAgeStr() {
        if(Age==0) return "";
        return Age+"";
    }
    public void setAgeStr(String ageStr) {
        AgeStr=ageStr;
        if(ageStr.equals("")) {
            Age=0;
            return;
        }
        Age=Integer.parseInt(ageStr);
    }
    public String Currentcity;
    public String IsLeft;
    public String Dep;
    public String Enable;
    long[] mHits = new long[2];        //存储时间的数组

    public ParticiantItemViewModel() {

    }

    @Override
    public String toString() {
        return Name+","+KeyCode;
    }

    public ParticiantItemViewModel(InputInforActivity activity, ActivityInputInforBinding binding) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        binding.contentInputInfor.setParticiant(this);
        binding.btInputOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(KeyCode) ) {
                    keyCodeError.set("编号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(Name)) {
                    nameError.set("名字不能为空");
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(GlobalParameterApplication.BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ParInfoService service = retrofit.create(ParInfoService.class);
                Call<Result<ParticiantItemViewModel>> repos =service.addPantInfos(ParticiantItemViewModel.this);
                repos.enqueue(new Callback<Result<ParticiantItemViewModel>>() {
                    @Override
                    public void onResponse(Call<Result<ParticiantItemViewModel>> call, Response<Result<ParticiantItemViewModel>> response) {
                        try {
                            Result<ParticiantItemViewModel> parInfoRespond= response.body();
                            if (parInfoRespond != null) {

                                Log.i("===",parInfoRespond.Data.toString());
                            }

                        } catch (Exception e) {
                            Log.e("===", "return:" + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<ParticiantItemViewModel>> call, Throwable t) {
                        Log.e("===", "失败");
                    }
                });
            }
        });
    }

    public void onItemClick(View view) {
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //Toast.makeText(view.getContext(),""+Id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            //传递name参数为tinyphp
            bundle.putInt("particpantId", Id);
            intent.putExtras(bundle);
            intent.setClass(view.getContext(), FunctionActivity.class);
            view.getContext().startActivity(intent);
        }
    }

}
