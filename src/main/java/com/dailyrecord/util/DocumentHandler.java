package com.dailyrecord.util;

import com.dailyrecord.RecordApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-4-5.
 */

public class DocumentHandler {
    private static Logger logger = LoggerFactory.getLogger(DocumentHandler.class);

    private static Configuration configuration = null;

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(RecordApplication.class, "/static/freemaker/");
    }

    public static void createDoc(String templateName, Map dataMap, String outPath) throws Exception {
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(templateName);
            t.setEncoding("utf-8");
            // 输出文档路径及名称
            File outFile = new File(outPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            t.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     *
     * @param dataMap
     */
    @SuppressWarnings("unchecked")
    private void getData(Map dataMap) {
        dataMap.put("meetingtime", "20170401");
        List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("purchaseTime", "进货日期" + i);
            map.put("product", "产品名称" + i);
            map.put("factory", "生产厂家" + i);
            map.put("spec", "产品规格" + i);
            map.put("number", "进货数量" + i);


            map.put("num", "1" + i);
            map.put("name", "姓名" + i);
            map.put("yesterdayc", "昨天" + i);
            map.put("todayc", "今天" + i);


            newsList.add(map);
        }
        dataMap.put("newsList", newsList);
    }
}
