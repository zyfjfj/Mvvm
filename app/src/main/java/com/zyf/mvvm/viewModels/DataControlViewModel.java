package com.zyf.mvvm.viewModels;

import android.databinding.BaseObservable;
import android.os.Handler;
import android.util.Log;

import com.zyf.mvvm.models.AssessResult;
import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/6/26.
 */

public class DataControlViewModel extends BaseObservable {
    public List<DataItemViewModel> dataItemViewModels=new ArrayList<DataItemViewModel>();

    public void initData(final Handler mHandler) {
        Subscriber<DatasWithPageInfo> subscriber=new Subscriber<DatasWithPageInfo>() {
            @Override
            public void onCompleted() {
                Log.i("dataswiht","1111");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("dataswiht","222");
            }

            @Override
            public void onNext(DatasWithPageInfo datasWithPageInfo) {
                if (datasWithPageInfo.Datamanagemodels != null) {
                    for (AssessResult assessResult :datasWithPageInfo.Datamanagemodels) {
                        DataItemViewModel dateItem=new DataItemViewModel();
                        dateItem.assessResult = assessResult;
                        dataItemViewModels.add(dateItem);
                    }
                    mHandler.sendEmptyMessage(0);
                }
                Log.i("dataswiht",datasWithPageInfo.Datamanagemodels.size()+"");
            }
        };
        RetrofitHelper.getInstance().getAssessResults(subscriber,90,1);
    }

}
