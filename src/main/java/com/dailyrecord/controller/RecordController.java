package com.dailyrecord.controller;

import com.dailyrecord.po.Config;
import com.dailyrecord.po.Record;
import com.dailyrecord.service.RecordService;
import com.dailyrecord.util.DateUtil;
import com.dailyrecord.util.DocumentHandler;
import com.dailyrecord.vo.ResVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Administrator on 2017-3-31.
 */

@Controller
@RequestMapping("/record")
public class RecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RecordService recordService;

    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public Object add(Record dailyRecord){
        ResVo res = new ResVo();
        try{
            recordService.add(dailyRecord);
            res.setSuccess("新增成功！", null);
        }catch (Exception e){
            logger.error("add error：", e);
            res.setFailure("-1", "新增异常！" + e.getMessage(), null);
        }
        return res;
    }
    @ResponseBody
    @RequestMapping(value="/listToday", method = RequestMethod.POST)
    public Object listToday(Record record){
        ResVo res = new ResVo();
        try{
            List<Record> list = recordService.listToday(record);
            res.setSuccess("查询今天成功！", list);
        }catch (Exception e){
            logger.error("listToday error：", e);
            res.setFailure("-1", "查询今天数据异常！" + e.getMessage(), null);
        }
        return res;
    }
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> downloadByGroup(HttpServletRequest req, String exFileName, Record record){
        try{
            logger.debug("downloadByGroup begin。record=" + record);
            String dateStr = DateUtil.date2Str(null);
            String baseDir = Config.getConfigCacheDir() + File.separator + "export" + File.separator + dateStr;
            File file = new File(baseDir);
            if(!file.exists()){
                file.mkdirs();
            }
            if(!file.canWrite()){
                logger.error("路径"+baseDir+"不能写！");
                throw new RuntimeException("配置的导出路径不能写。");
            }
            exFileName = URLDecoder.decode(exFileName, "UTF-8");
            String fileName = exFileName + dateStr + ".doc";
            baseDir += File.separator+fileName;
            Map dataMap = recordService.queryForExport(record);
            DocumentHandler.createDoc("exportWord.ftl", dataMap, baseDir);
            String dfileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", dfileName);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(baseDir)), headers, HttpStatus.CREATED);
        }catch(Exception e){
            logger.error("downloadByGroup error:", e);
        }
        return null;
    }
}
