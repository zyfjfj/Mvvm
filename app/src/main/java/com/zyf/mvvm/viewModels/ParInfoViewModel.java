package com.zyf.mvvm.viewModels;

import android.os.Handler;

import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyf on 2017/6/27.
 */

public class ParInfoViewModel {
    public List<ParticiantItemViewModel> particiantItemViewModels = new ArrayList<ParticiantItemViewModel>();

    public void initData(final Handler mHandler) {
        Subscriber<ParInfoWithPageInfo> subscriber = new Subscriber<ParInfoWithPageInfo>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(ParInfoWithPageInfo parInfoWithPageInfo) {
                if (parInfoWithPageInfo.Participants != null) {
                    for (Particiant parInfo : parInfoWithPageInfo.Participants) {
                        ParticiantItemViewModel particintItem = new ParticiantItemViewModel();
                        particintItem.mParticiant = parInfo;
                        particiantItemViewModels.add(particintItem);
                    }

                    mHandler.sendEmptyMessage(0);
                }
            }
        };
        RetrofitHelper.getInstance().getpantinfos(subscriber, 90, 1);
    }
}
