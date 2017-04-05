package com.dailyrecord.vo;

/**
 * Created by Administrator on 2017-3-31.
 */
public class ResVo {
    // 返回结果
    private String code = "0";
    // 结果描述
    private String desc = "操作成功";
    private Object obj;

    public void setSuccess(String code, String desc, Object obj){
        this.code = code;
        this.desc = desc;
        this.obj = obj;
    }
    public void setSuccess(String desc, Object obj){
        this.code = "0";
        this.desc = desc;
        this.obj = obj;
    }

    public void setFailure(String code, String desc, Object obj){
        this.code = code;
        this.desc = desc;
        this.obj = obj;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ResVo{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", obj=" + obj +
                '}';
    }
}
