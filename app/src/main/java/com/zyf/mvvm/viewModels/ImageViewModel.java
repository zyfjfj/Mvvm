package com.zyf.mvvm.viewModels;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zyf.mvvm.GlobalParameterApplication;
import com.zyf.mvvm.databinding.ActivityImageBinding;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.Sence;
import com.zyf.mvvm.net.RetrofitHelper;
import com.zyf.mvvm.utils.FileOper;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/7/14.
 */

public class ImageViewModel {
    public ImageViewModel(ActivityImageBinding binding) {
        mBinding=binding;
    }
    ActivityImageBinding mBinding;
    List<String > mImageName;
    public ObservableField<String> imageUrl =new ObservableField<>();
    public String getImageUrl() {
        return imageUrl.get();
    }
    public void setImageUrl(String imageUrl){
        imageUrl="file://"+GlobalParameterApplication.file_path+"/"+imageUrl+".jpg";
        this.imageUrl.set(imageUrl);
    }
    @BindingAdapter("bind:imageUrl")
    public static void  loadImage(ImageView iv,String imageUrl){
        Picasso.with(iv.getContext()).load(imageUrl).into(iv);
    }
    public void init(){
        Subscriber<List<Sence>> subscriber=new Subscriber<List<Sence>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Sence> sences) {
                mImageName=new ArrayList<>();
                for (Sence sence : sences) {
                    Log.i("sence",sence.Name);
                    FileOper.generateImage(sence.Name,sence.Data);
                    mImageName.add(sence.Name);
                }
            }
        };
        Sence.Ids ids=new Sence.Ids();
        int[] sids=new int[80];
        for(int i=0;i<80;i++){
            sids[i]=i;
        }
        ids.thestring= sids;
        RetrofitHelper.getInstance().getSences(subscriber,ids);
    }
    public void OnClick(View v){
        if(mImageName.size()>0){
            setImageUrl(mImageName.get(mImageName.size() - 1));
            mBinding.setImageViewModel(this);
            mImageName.remove(mImageName.size() - 1);
        }
    }
}
