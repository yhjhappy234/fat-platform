package com.yhj.schedule;

import com.yhj.exception.PopException;
import com.yhj.executor.SimpleExecutor;
import com.yhj.util.SpringUtil;
import com.yhj.widget.SimpleTask;
import com.yhj.widget.WidgetFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by YHJ on 2016/12/11.
 */
@Service
public class SimpleSchedule implements FactoryBean<SimpleSchedule> {

    private Log log = LogFactory.getLog(SimpleSchedule.class);

    private SimpleSchedule() {
    }

    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean init = new AtomicBoolean(false);

    private ExecutorService service = Executors.newFixedThreadPool(20);

    private SimpleExecutor simpleExecutor;

    public void start(){
        if(!running.compareAndSet(false,true)){
            log.warn("task is still running ,just skip");
        }
    }

    public void stop(){
        running.set(false);
    }

    protected boolean isInited(){
        return init.get();
    }

    protected void init(){
        if(!init.compareAndSet(false,true)){
            return;
        }
        while (true){
            if(running.get()){
                try {
                    final SimpleTask simpleTask = WidgetFactory.pop();
                    service.execute(new Runnable() {
                        @Override
                        public void run() {
                            simpleExecutor.execute(simpleTask);
                        }
                    });
                } catch (PopException e) {
                    log.error("execute task fail ",e);
                }
            }else{
                log.info("execute has stop,just wait for start");
                try {
                    TimeUnit.MILLISECONDS.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public SimpleSchedule getObject() throws Exception {
        final SimpleSchedule simpleSchedule = new SimpleSchedule();
        simpleSchedule.simpleExecutor = SpringUtil.getBean(SimpleExecutor.class);
        new Thread(){
            @Override
            public void run() {
                simpleSchedule.init();
            }
        }.start();
        simpleSchedule.start();//默认开启
        return simpleSchedule;
    }

    @Override
    public Class<SimpleSchedule> getObjectType() {
        return SimpleSchedule.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
