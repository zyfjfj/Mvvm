package com.zyf.mvvm.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zyf.mvvm.databinding.ScaleItemBinding;
import com.zyf.mvvm.databinding.ScaleSubjectItemBinding;
import com.zyf.mvvm.models.ScaleItem;
import com.zyf.mvvm.models.ScaleSubject;
import com.zyf.mvvm.viewModels.ScaleItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyf on 2017/7/10.
 */

public class ScaleSubjectAdapter extends RecyclerView.Adapter<ScaleSubjectAdapter.ScaleSubjectHolder> {
    private List<ScaleItemViewModel> mData = new ArrayList<>();
    public ScaleSubjectAdapter(List<ScaleItemViewModel> data) {
        this.mData = data;
    }

    @Override
    public ScaleSubjectAdapter.ScaleSubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ScaleSubjectAdapter.ScaleSubjectHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ScaleSubjectAdapter.ScaleSubjectHolder holder, int position) {
        holder.bindTo(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    static class ScaleSubjectHolder extends RecyclerView.ViewHolder {
        private ScaleSubjectItemBinding mBinding;
        private int i=0;

        static ScaleSubjectHolder create(LayoutInflater inflater, ViewGroup parent) {
            ScaleSubjectItemBinding binding = ScaleSubjectItemBinding.inflate(inflater, parent, false);
            return new ScaleSubjectHolder(binding);
        }

        private ScaleSubjectHolder(ScaleSubjectItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(ScaleItemViewModel scaleItemViewModel) {
            mBinding.answers.removeAllViews();
            Log.i("bing",i+++"");
            RadioGroup group = new RadioGroup(mBinding.getRoot().getContext());
            for (ScaleItem.Answer answer : scaleItemViewModel.mScaleItem.Answers) {
                RadioButton radio = new RadioButton(mBinding.getRoot().getContext());
                radio.setText(answer.name);
                radio.setTextSize(20);
                group.addView(radio);
            }
            mBinding.answers.addView(group);
            mBinding.setScaleItemViewModel(scaleItemViewModel);
            mBinding.executePendingBindings();
        }

    }
}
