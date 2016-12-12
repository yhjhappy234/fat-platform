package com.yhj.web;

import com.yhj.schedule.SimpleSchedule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by YHJ on 2016/12/11.
 */
@Controller
public class OpsController {

    @Resource
    private SimpleSchedule simpleSchedule;

    @RequestMapping("/start")
    @ResponseBody
    public String start(){
        simpleSchedule.start();
        return "started";
    }

    @RequestMapping("/stop")
    @ResponseBody
    public String stop(){
        simpleSchedule.stop();
        return "stoped";
    }
}
