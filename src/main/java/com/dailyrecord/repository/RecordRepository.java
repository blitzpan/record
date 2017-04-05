package com.dailyrecord.repository;

import com.dailyrecord.po.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-3-21.
 */
public interface RecordRepository extends JpaRepository<Record, Long> {
    /**
     * 根据时间段来查询
     * @param start
     * @param end
     * @return
     */
    List<Record> findByGroupNameAndCurDateBetween(String groupName,Date start, Date end);

    List<Record> findByGroupNameAndNameAndCurDateBetween(String groupName, String name,Date start, Date end);
}
