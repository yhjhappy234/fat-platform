package com.yhj.executor;

import com.yhj.widget.TaskResult;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by YHJ on 2016/12/11.
 */
public class TaskResultCenter {

    //此处先用map实现，后续考虑存储到DB中，通过DB查询
    private static final Map<String,TaskResult> taskResultMap = new ConcurrentHashMap<String, TaskResult>();

    public static void put(String taskId,TaskResult result){
        taskResultMap.put(taskId,result);
    }

    public static TaskResult getTaskResult(String taskId){
        return taskResultMap.get(taskId);
    }
    public static Collection<TaskResult> getAllTaskResult(){
        return taskResultMap.values();
    }
}
