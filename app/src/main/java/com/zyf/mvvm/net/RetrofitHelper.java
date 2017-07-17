package com.zyf.mvvm.net;

import com.zyf.mvvm.models.AssessResult;
import com.zyf.mvvm.models.DatasWithPageInfo;
import com.zyf.mvvm.models.ParInfoWithPageInfo;
import com.zyf.mvvm.models.Particiant;
import com.zyf.mvvm.models.ProgramControl;
import com.zyf.mvvm.models.Result;
import com.zyf.mvvm.models.ScaleItem;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.models.Sence;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zyf on 2017/7/4.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private final String BASEURL = "http://192.168.0.116:9999/";
    private Retrofit retrofit;
    private OkHttpClient.Builder builder;

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    public RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .build();
    }


    /**
     * 获取测评结果
     *
     * @param subscriber
     * @param pageSize   每页数据数量
     * @param curPage    第几页
     */
    public void getAssessResults(Subscriber<DatasWithPageInfo> subscriber, int pageSize, int curPage) {
        AssessResultService service = retrofit.create(AssessResultService.class);
        AssessResult assessResult = new AssessResult();
        assessResult.pagesize = pageSize;
        assessResult.curpage = curPage;
        service.getAssessResults(assessResult)
                .map(new Func1<Result<DatasWithPageInfo>, DatasWithPageInfo>() {
                    @Override
                    public DatasWithPageInfo call(Result<DatasWithPageInfo> datasWithPageInfoResult) {
                        return datasWithPageInfoResult.Data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 根据测试编号获取被试数据
     *
     * @param subscriber
     * @param KeyCode
     */
    public void getPantinfo(Subscriber<List<Particiant>> subscriber, String KeyCode) {
        ParInfoService service = retrofit.create(ParInfoService.class);
        service.getPantinfo(KeyCode)
                .map(new Func1<Result<List<Particiant>>, List<Particiant>>() {
                    @Override
                    public List<Particiant> call(Result<List<Particiant>> listResult) {
                        return listResult.Data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 增加被试信息
     *
     * @param subscriber
     * @param particiant
     */
    public void addPantInfos(Subscriber<Particiant> subscriber, Particiant particiant) {
        ParInfoService service = retrofit.create(ParInfoService.class);
        service.addPantInfos(particiant)
                .map(new Func1<Result<Particiant>, Particiant>() {
                    @Override
                    public Particiant call(Result<Particiant> particiantResult) {
                        return particiantResult.Data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取所有被试信息
     *
     * @param subscriber
     * @param pageSize   每页数据数量
     * @param curPage    第几页
     */
    public void getpantinfos(Subscriber<ParInfoWithPageInfo> subscriber, int pageSize, int curPage) {
        ParInfoWithPageInfo parInfoWithPageInfo = new ParInfoWithPageInfo();
        parInfoWithPageInfo.pagesize = pageSize;
        parInfoWithPageInfo.curpage = curPage;

        ParInfoService service = retrofit.create(ParInfoService.class);

        service.getpantinfos(parInfoWithPageInfo)
                .map(new Func1<Result<ParInfoWithPageInfo>, ParInfoWithPageInfo>() {
                    @Override
                    public ParInfoWithPageInfo call(Result<ParInfoWithPageInfo> parInfoWithPageInfo) {
                        return parInfoWithPageInfo.Data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 设置测试状态，用于控制warmhug
     *
     * @param subscriber
     * @param programControl Subscriber<ProgramControl> 尖括号里是要返回的数据
     */
    public void setProgramStatus(Subscriber<ProgramControl> subscriber, ProgramControl programControl) {
        ProgramControlService service = retrofit.create(ProgramControlService.class);
        service.setProgramStatus(programControl)
                .map(new Func1<Result<ProgramControl>, ProgramControl>() {
                    @Override
                    public ProgramControl call(Result<ProgramControl> programControlResult) {
                        return programControlResult.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取所有的量表测试项目
     *
     * @param subscriber
     */
    public void getScaleSubjects(Subscriber<List<ScaleSubject>> subscriber) {
        ScaleSubjectService service = retrofit.create(ScaleSubjectService.class);
        service.getScaleSubjects()
                .map(new Func1<Result<List<ScaleSubject>>, List<ScaleSubject>>() {

                    @Override
                    public List<ScaleSubject> call(Result<List<ScaleSubject>> listResult) {
                        return listResult.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 根据量表id获取题目及答案选项
     *
     * @param subscriber
     */
    public void getItemAnswerByScaleId(Subscriber<List<ScaleItem>> subscriber, int id) {
        ScaleSubjectService service = retrofit.create(ScaleSubjectService.class);
        service.getItemAnswerByScaleId(id)
                .map(new Func1<Result<List<ScaleItem>>, List<ScaleItem>>() {
                    @Override
                    public List<ScaleItem> call(Result<List<ScaleItem>> scaleItemResult) {
                        return scaleItemResult.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void getSences(Subscriber<List<Sence>> subscriber, Sence.Ids ids) {
        ImageService service = retrofit.create(ImageService.class);
        service.getImages(ids)
                .map(new Func1<Result<List<Sence>>, List<Sence>>() {

                    @Override
                    public List<Sence> call(Result<List<Sence>> listResult) {
                        return listResult.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
