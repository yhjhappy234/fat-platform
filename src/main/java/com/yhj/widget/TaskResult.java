package com.yhj.widget;

import java.io.Serializable;

/**
 * Created by YHJ on 2016/12/11.
 */
public class TaskResult implements Serializable {

    private static final long serialVersionUID = -1460239200559602604L;
    private boolean success = true;
    private String msg;
    private Object obj;

    public TaskResult(Object obj) {
        this.obj = obj;
    }

    public TaskResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
