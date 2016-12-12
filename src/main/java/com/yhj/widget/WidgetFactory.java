package com.yhj.widget;

import com.yhj.exception.PopException;
import com.yhj.exception.RegisterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by YHJ on 2016/12/11.
 */
public class WidgetFactory {
    private static Log log = LogFactory.getLog(WidgetFactory.class);

    //先进先出队列
    private static BlockingQueue<SimpleTask> simpleTaskQueue = new LinkedBlockingQueue<SimpleTask>();
    private static BlockingQueue<CronTask> cronTaskBlockingQueue = new LinkedBlockingQueue<CronTask>();

    public static String register(SimpleTask task) throws RegisterException {
        try {
            String taskId = task.initTask();
            simpleTaskQueue.put(task);
            log.info("[put] queue size "+simpleTaskQueue.size());
            return taskId;
        } catch (InterruptedException e) {
            throw new RegisterException("some other thread interrupt this task,just skip",e);
        }
    }

    public static SimpleTask pop() throws PopException {
        try {
            SimpleTask task = simpleTaskQueue.take();
            log.info("[pop "+task.getTaskId()+"] queue size "+simpleTaskQueue.size());
            return task;
        } catch (InterruptedException e) {
            throw new PopException("some other thread interrupt this task,just skip",e);
        }
    }
}
