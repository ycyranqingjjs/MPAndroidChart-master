package com.xxmassdeveloper.mpchartexample.custom;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.Bean.webData;

/**
 * Created by philipp on 02/06/16.
 */
public class DayAxisValueFormatter_xx implements AxisValueFormatter {

    private BarLineChartBase<?> chart;
    private webData mData;

    public DayAxisValueFormatter_xx(BarLineChartBase<?> chart, webData mData) {
        this.chart = chart;
        this.mData = mData;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

//        return mData.getmSwitchTime().get(((int)(value)%mData.getmSwitchTime().size())).getKey();
        if (mData!=null){

            if (value<mData.getmSwitchTime().size()){
                return mData.getmSwitchTime().get((int)(value)).getKey();
            }else
                return "";
        }else
            return "";
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
