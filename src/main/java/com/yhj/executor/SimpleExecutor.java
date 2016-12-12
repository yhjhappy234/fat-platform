package com.yhj.executor;

import com.yhj.widget.SimpleTask;
import com.yhj.widget.TaskResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by YHJ on 2016/12/11.
 */
@Service
public class SimpleExecutor {

    private Log log = LogFactory.getLog(SimpleExecutor.class);

    public void execute(SimpleTask task){
        try{
            TaskResult result = task.execute();
            TaskResultCenter.put(task.getTaskId(),result);
        }catch (Throwable e){
            log.error("exec task ["+task.getTaskId()+"/"+task.getName()+"] fail",e);
            TaskResultCenter.put(task.getTaskId(),new TaskResult(false,e.getMessage()));
        }
    }
}
