package com.yhj.web;

import com.yhj.exception.RegisterException;
import com.yhj.executor.TaskResultCenter;
import com.yhj.widget.TaskResult;
import com.yhj.widget.WidgetFactory;
import com.yhj.widget.tasks.MoguTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by YHJ on 2016/12/11.
 */
@Controller
public class SimpleController {

    @RequestMapping("/register")
    @ResponseBody
    public String register() throws RegisterException {
        return WidgetFactory.register(new MoguTask());
    }

    @ResponseBody
    @RequestMapping("/result")
    public Collection<TaskResult> result(){
        return TaskResultCenter.getAllTaskResult();
    }
}
