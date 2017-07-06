package com.zyf.mvvm.viewModels;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityInputInforBinding;
import com.zyf.mvvm.models.PageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.net.RetrofitHelper;
import com.zyf.mvvm.views.FunctionActivity;
import com.zyf.mvvm.views.InputInforActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/6/20.
 */

public class ParticiantItemViewModel{
    public Particiant mParticiant;
    public ObservableField<String> keyCodeError = new ObservableField<>();
    public ObservableField<String> nameError = new ObservableField<>();

    public String getBirthdayStr() {
        Date date = new Date();
        mParticiant.Age = date.getYear() - mParticiant.Birthday.getYear();
        return new SimpleDateFormat("yyyy-MM-dd").format(mParticiant.Birthday);
    }

    public String KeyCode;
    public ObservableField<String> Name = new ObservableField<>();
    public ObservableField<String> AgeStr = new ObservableField<>();
    public ObservableField<String> Phone = new ObservableField<>();
    long[] mHits = new long[2];        //存储时间的数组
    private int mCheckid = 0;

    public ParticiantItemViewModel() {

    }

    public ParticiantItemViewModel(final InputInforActivity activity, ActivityInputInforBinding binding) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        binding.contentInputInfor.setParticiant(this);
        binding.btInputOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(KeyCode)) {
                    keyCodeError.set("编号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(Name.get())) {
                    nameError.set("名字不能为空");
                    return;
                }
                Particiant particiant = new Particiant();
                particiant.Age = Integer.parseInt(AgeStr.get());
                particiant.Name = Name.get();
                particiant.KeyCode = KeyCode;
                particiant.Phone = Phone.get();
                particiant.Enable = "Y";
                particiant.Sex = 1;
                if (mCheckid != 0) {
                    RadioButton radioButton = (RadioButton) activity.findViewById(mCheckid);
                    particiant.Sex = radioButton.getText().toString() == "男" ? 1 : 0;
                }
                Subscriber<Particiant> subscriber = new Subscriber<Particiant>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Particiant particiant) {
                        Log.i("add",particiant.KeyCode);
                    }
                };
                RetrofitHelper.getInstance().addPantInfos(subscriber,particiant);

            }
        });
    }

    public void onCheckedChange(RadioGroup Group, int Checkid) {
        mCheckid = Checkid;
    }

    public void onItemClick(View view) {
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();

        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            //传递name参数为tinyphp
            bundle.putInt("particpantId", mParticiant.Id);
            intent.putExtras(bundle);
            intent.setClass(view.getContext(), FunctionActivity.class);
            view.getContext().startActivity(intent);
        }
    }

    //点击输入控件
    public void onTextViewClick(View view) {
        if (!KeyCode.equals("")) {
            Subscriber<List<Particiant>> subscriber = new Subscriber<List<Particiant>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable throwable) {
                }

                @Override
                public void onNext(List<Particiant> particiants) {
                    if (particiants != null) {
                        Date date = new Date();
                        mParticiant = particiants.get(0);
                        mParticiant.Age = date.getYear() - mParticiant.Birthday.getYear();
                        Name.set(mParticiant.Name);
                        AgeStr.set(mParticiant.Age + "");
                        Phone.set(mParticiant.Phone);
                    } else {
                        Name.set("");
                        AgeStr.set("");
                        Phone.set("");
                    }
                }
            };
            RetrofitHelper.getInstance().getPantinfo(subscriber,KeyCode);
        }
    }

}
