package com.dailyrecord.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017-3-21.
 * Entity注解表名这是一个jpa实体
 */

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    private String name;
    @Column(length = 1024)
    private String yesterdayC;
    @Column(length = 1024)
    private String todayC;
    private Date curDate;
    @Transient
    private Date start;//起止时间
    @Transient
    private Date end;//起止时间

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", name='" + name + '\'' +
                ", yesterdayC='" + yesterdayC + '\'' +
                ", todayC='" + todayC + '\'' +
                ", curDate=" + curDate +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYesterdayC() {
        return yesterdayC;
    }

    public void setYesterdayC(String yesterdayC) {
        this.yesterdayC = yesterdayC;
    }

    public String getTodayC() {
        return todayC;
    }

    public void setTodayC(String todayC) {
        this.todayC = todayC;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
}
