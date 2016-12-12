package com.yhj.widget.tasks;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhj.widget.SimpleTask;
import com.yhj.widget.TaskResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHJ on 2016/12/11.
 */
public class MoguTask extends SimpleTask {

    private String pageUrl;

    private Log log = LogFactory.getLog(MoguTask.class);
    public MoguTask() {
        super("MoguTask");
    }

    @Override
    public TaskResult execute() {
        List<String> url = new ArrayList<String>();
        try {
            Document html = Jsoup.connect("http://1212.mogujie.com/branch/dress/index?acm=3.mce.1_10_19ajg.28859.0.qxkq55oaYa2.m_216754&ptp=1._mf1_1386_21715.0.0.hOhR4").get();
            Document doc = Jsoup.parse(html.body().html());//解析HTML字符串返回一个Document实现
            Elements div = doc.getElementsByTag("input");
            for (Element div1 : div) {
                String pid = div1.attr("datamoduletab");
                if (pid != "" & pid != null) {
                    JSONArray list = JSONArray.parseArray(pid);
                    int j = list.size();
                    for (int i = 0; i < j; i++) {
                        JSONObject a = list.getJSONObject(i);

                        url.add(a.get("tabName") + "url:" + "<a href=" + "'" + a.get("tabUrl") + "'>" + a.get("tabUrl") + "</a>" + ";<br/>");
                    }


                }
            }
            return new TaskResult(url);
        }catch (IOException e) {
            log.error("====>",e);
            return new TaskResult(false,e.getMessage());
        }
    }
}
