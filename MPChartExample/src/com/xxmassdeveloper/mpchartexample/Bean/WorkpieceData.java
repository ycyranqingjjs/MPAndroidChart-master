package com.xxmassdeveloper.mpchartexample.Bean;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class WorkpieceData {//工件统计
    private String key;
    private String data;
    @Override
    public String toString() {
        return "DowntimeData{" +
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
