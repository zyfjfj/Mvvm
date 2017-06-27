package com.zyf.mvvm.models;

import java.util.List;

/**
 * Created by zyf on 2017/6/20.
 */

public class Result<T> extends PageInfo{
    private boolean success;
    private String Message;
    private int count;
    private T Data;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }
}
