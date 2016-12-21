package com.xxmassdeveloper.mpchartexample.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class webData {
    private int dailyoutput;//日产量
    private int unproducts;//不合格品
    private double dprate;//日计划完成率
    private double startrate;//开动率
    private double oee;//OEE
    private int energy;//单体设备能耗
    private int workpiecenumber;//工件号
    private int singletact;//单件生产节拍
    public ArrayList<DlarmData> mDlarmData;  //报警统计
    public ArrayList<DowntimeData> mDowntimeData;//停机原因统计
    public ArrayList<MachineStatusData> mMachineStatusData;//机床状态统计
    public ArrayList<SwitchTime> mSwitchTime;//开关机时间统计
    public ArrayList<WorkpieceData> mWorkpieceData;//工件统计

    @Override
    public String toString() {
        return "webData{" +
                "dailyoutput=" + dailyoutput +
                ", unproducts=" + unproducts +
                ", dprate=" + dprate +
                ", startrate=" + startrate +
                ", oee=" + oee +
                ", energy=" + energy +
                ", workpiecenumber=" + workpiecenumber +
                ", singletact=" + singletact +
                ", mDlarmData=" + mDlarmData +
                ", mDowntimeData=" + mDowntimeData +
                ", mMachineStatusData=" + mMachineStatusData +
                ", mSwitchTime=" + mSwitchTime +
                ", mWorkpieceData=" + mWorkpieceData +
                '}';
    }

    public void setDailyoutput(int dailyoutput) {
        this.dailyoutput = dailyoutput;
    }

    public void setUnproducts(int unproducts) {
        this.unproducts = unproducts;
    }

    public void setDprate(double dprate) {
        this.dprate = dprate;
    }

    public void setStartrate(double startrate) {
        this.startrate = startrate;
    }

    public void setOee(double oee) {
        this.oee = oee;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setWorkpiecenumber(int workpiecenumber) {
        this.workpiecenumber = workpiecenumber;
    }

    public void setSingletact(int singletact) {
        this.singletact = singletact;
    }

    public void setmDlarmData(ArrayList<DlarmData> mDlarmData) {
        this.mDlarmData = mDlarmData;
    }

    public void setmDowntimeData(ArrayList<DowntimeData> mDowntimeData) {
        this.mDowntimeData = mDowntimeData;
    }

    public void setmMachineStatusData(ArrayList<MachineStatusData> mMachineStatusData) {
        this.mMachineStatusData = mMachineStatusData;
    }

    public void setmSwitchTime(ArrayList<SwitchTime> mSwitchTime) {
        this.mSwitchTime = mSwitchTime;
    }

    public void setmWorkpieceData(ArrayList<WorkpieceData> mWorkpieceData) {
        this.mWorkpieceData = mWorkpieceData;
    }

    public int getDailyoutput() {

        return dailyoutput;
    }

    public int getUnproducts() {
        return unproducts;
    }

    public double getDprate() {
        return dprate;
    }

    public double getStartrate() {
        return startrate;
    }

    public double getOee() {
        return oee;
    }

    public int getEnergy() {
        return energy;
    }

    public int getWorkpiecenumber() {
        return workpiecenumber;
    }

    public int getSingletact() {
        return singletact;
    }

    public ArrayList<DlarmData> getmDlarmData() {
        return mDlarmData;
    }

    public ArrayList<DowntimeData> getmDowntimeData() {
        return mDowntimeData;
    }

    public ArrayList<MachineStatusData> getmMachineStatusData() {
        return mMachineStatusData;
    }

    public ArrayList<SwitchTime> getmSwitchTime() {
        return mSwitchTime;
    }

    public ArrayList<WorkpieceData> getmWorkpieceData() {
        return mWorkpieceData;
    }

}

//DlarmData:报警统计
// DowntimeData:停机原因统计
//  WorkpieceData:工件统计
// MachineStatusData :机床状态统计
// SwitchTime :开关机时间统计
//    其中图表的"key"字段为第一列 "data"为第二列数据值

 /*
    {
    "dailyoutput":29,
    "unproducts":3,
    "dprate":0.13,
    "startrate":0.63,
    "oee":1.16,
    "energy":23,
    "workpiecenumber":4,
    "singletact":9,
    "DlarmData":[
         {"key":"温度过高",
             "data"21:,
           },{"key":"工作时间太长",
         "data":25,
          },{"key":"其他原因",
          "data":27,
          }
    ], "DowntimeData":[
         {"key":"温度过高",
             "data"21:,
           },{"key":"工作时间太长",
         "data":25,
          },{"key":"其他原因",
          "data":27,
          }
    ],"MachineStatusData":[
         {"key":"温度过高",
             "data"21:,
           },{"key":"工作时间太长",
         "data":25,
          },{"key":"其他原因",
          "data":27,
          }
    ],
    "SwitchTime":[
         {"key":"温度过高",
             "data"21:,
           },{"key":"工作时间太长",
         "data":25,
          },{"key":"其他原因",
          "data":27,
          }
    ],
     "WorkpieceData":[
         {"key":"温度过高",
             "data"21:,
           },{"key":"工作时间太长",
         "data":25,
          },{"key":"其他原因",
          "data":27,
          }
    ],

    }
     */