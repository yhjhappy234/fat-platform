package com.yhj.widget;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by YHJ on 2016/12/11.
 */
public abstract class SimpleTask implements Serializable{

    private String name;

    private String taskId;

    public SimpleTask(String name) {
        this.name = name;
    }

    public abstract TaskResult execute();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String initTask(){
        String uuid = UUID.randomUUID().toString();
        this.taskId = uuid;
        return uuid;
    }

    public String getTaskId() {
        return taskId;
    }
}
