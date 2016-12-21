package com.xxmassdeveloper.mpchartexample.Bean;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class DlarmData {  //报警统计
    private String key;
    private String data;

    @Override
    public String toString() {
        return "DlarmData{" +
                "key='" + key + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {

        return key;
    }

    public String getData() {
        return data;
    }
}
