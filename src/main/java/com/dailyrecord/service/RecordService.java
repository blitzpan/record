package com.dailyrecord.service;

import com.dailyrecord.po.Record;
import com.dailyrecord.repository.RecordRepository;
import com.dailyrecord.util.DateUtil;
import com.dailyrecord.vo.ResVo;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017-3-31.
 */

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public ResVo add(Record dailyRecord) throws Exception{
        ResVo res = new ResVo();
        if(StringUtils.isEmpty(dailyRecord.getName())){
            res.setFailure("-1", "姓名不能为空！", null);
            return res;
        }
        dailyRecord.setCurDate(new Date());
        Date[] today = this.getToday();
        List<Record> list = recordRepository.findByGroupNameAndNameAndCurDateBetween(dailyRecord.getGroupName(), dailyRecord.getName(), today[0], today[1]);
        if(!list.isEmpty()){
            dailyRecord.setId(list.get(0).getId());
        }
        recordRepository.save(dailyRecord);
        return res;
    }

    /**
     * 查询今日记录
     * @param record
     * @return
     * @throws Exception
     */
    public List<Record> listToday(Record record) throws Exception{
        Date[] today = this.getToday();
//        Example<Record> example = Example.of(record);
        return recordRepository.findByGroupNameAndCurDateBetween(record.getGroupName(), today[0], today[1]);
    }
    public Map queryForExport(Record record) throws Exception{
        Map result = new HashMap();
        Date[] today = this.getToday();
        List<Record> records = listToday(record);
        //开会日期
        result.put("meetingtime", DateUtil.date2Str(null));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i=0; i<records.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num", i + 1);
            map.put("name", records.get(i).getName());
            map.put("yesterdayc", records.get(i).getYesterdayC());
            map.put("todayc", records.get(i).getTodayC());
            list.add(map);
        }
        result.put("records", list);
        return result;
    }

    /**
     * 获取今天起止时间
     * @return
     * @throws Exception
     */
    private Date[] getToday() throws Exception{
        Date[] date = new Date[2];
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        date[0] = now.getTime();
        now.add(Calendar.DAY_OF_YEAR, 1);
        date[1] = now.getTime();
        return date;
    }
}
