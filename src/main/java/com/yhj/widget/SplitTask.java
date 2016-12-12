package com.yhj.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHJ on 2016/12/11.
 */
public abstract class SplitTask extends SimpleTask{

    public SplitTask(String name) {
        super(name);
    }

    @Override
    public TaskResult execute() {
        boolean success = true;
        List<SimpleTask> tasks = getTasks();
        List<TaskResult> results = new ArrayList<TaskResult>(tasks.size());
        for(SimpleTask task:tasks){
            TaskResult result = handleOne(task);
            success = success && result.isSuccess();
            results.add(result);
        }
        TaskResult finalResult = new TaskResult(results);
        finalResult.setSuccess(success);
        return finalResult;
    }

    abstract List<SimpleTask> getTasks();

    abstract TaskResult handleOne(SimpleTask simpleTask);

}
