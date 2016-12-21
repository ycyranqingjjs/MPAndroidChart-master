package com.xxmassdeveloper.mpchartexample.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.xxmassdeveloper.mpchartexample.Bean.webData;
import com.xxmassdeveloper.mpchartexample.R;
import com.xxmassdeveloper.mpchartexample.Utils.StreamUtils;
import com.xxmassdeveloper.mpchartexample.Xiangxi;
import com.xxmassdeveloper.mpchartexample.custom.DayAxisValueFormatter_xx;
import com.xxmassdeveloper.mpchartexample.custom.MyAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.XYMarkerView;
import com.xxmassdeveloper.mpchartexample.global.GlobalContants;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CaseActivity extends DemoBase implements
        OnChartValueSelectedListener, View.OnClickListener {
    protected static final int CODE_NET_ERROR = 2;//网络错误
    protected static final int CODE_URL_ERROR = 1;// url错误
    public static final String TAG = CaseActivity.class.getSimpleName();
    private PieChart mChart;
    private BarChart mChart2;
    private static int biao = 0;
    private Button gongzuotongji;
    private Button baojinlv;
    private Button jichuandongtaitongji;
    private Button jichuangtingjiyy;
    private Button jichuangkaiguansji;

    private TextView xxdata;


    private String mac_no;
    private String scx_no;
    private String date_no;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private webData mData;
    private String server_url;

    private TextView richanliang;
    private TextView buhegelv;
    private TextView wanchenlv;
    private TextView dantinenghao;
    private TextView kaidonglv;
    private TextView gongjianhao;
    private TextView oee;
    private Calendar cal;

    private TextView date;
    private TextView mac;
    private TextView scx;


    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    String[] jichuangNober = {"M01", "MO2", "M03-1", "M03-2", "M04-1", "M04-2", "M05-1",
            "M05-2", "M06-1", "M06-2"};


    private List<String> list2 = new ArrayList<String>();
    private Spinner mySpinner2;
    private ArrayAdapter<String> adapter2;

    String[] jichuangNober2 = {"01", "O2", "03", "04",
            "05"};


    private long startTime;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case CODE_NET_ERROR:
                    Toast.makeText(CaseActivity.this, "网络异常", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(CaseActivity.this, "url网络异常", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 3:
                    webData mD = (webData) msg.obj;
                    richanliang.setText(mD.getDailyoutput() + "");
                    buhegelv.setText(mD.getUnproducts() + "");//不合格品
                    wanchenlv.setText(mD.getDprate() + "");
                    dantinenghao.setText(mD.getEnergy() + "");
                    kaidonglv.setText(mD.getStartrate() + "");
                    oee.setText(mD.getOee() + "");
                    gongjianhao.setText(mD.getWorkpiecenumber() + "/" + mD.getSingletact());
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

        mac_no = new String();
        scx_no = new String();
        date_no = new String();

        initView();

        //获取当前系统的时间
        startTime = System.currentTimeMillis();

        mac_no = mac.getText().toString();
        scx_no = scx.getText().toString();
        date_no = date.getText().toString();

        initData();
        initPri();
        initPri2();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initPri2() {
        mChart2 = (BarChart) findViewById(R.id.chart2);
        mChart2.setOnChartValueSelectedListener(this);

        mChart2.setDrawBarShadow(false);
        mChart2.setDrawValueAboveBar(true);

        mChart2.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart2.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart2.setPinchZoom(false);

        mChart2.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);
        mChart2.animateXY(3000, 3000);
        AxisValueFormatter xAxisFormatter = new DayAxisValueFormatter_xx(mChart2, mData);

        XAxis xAxis = mChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);


        AxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = mChart2.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart2.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        Legend l = mChart2.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        mChart2.setMarkerView(new XYMarkerView(this, xAxisFormatter));

        setData2(12, 50);
        // mChart.setDrawLegend(false);
    }


    private void setData2(int count, float range) {

        float start = 0f;

        mChart2.getXAxis().setAxisMinValue(start);
        mChart2.getXAxis().setAxisMaxValue(start + count + 2);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        if (mData != null && mData.getmSwitchTime().size() > 0)
            for (int i = 0; i < mData.getmSwitchTime().size(); i++) {
                float val = Float.parseFloat(mData.getmSwitchTime().get(i).getData());
                yVals1.add(new BarEntry(i + 1f, val));
            }
        else if (mData == null || mData.getmSwitchTime().size() <= 0) {
            for (int i = (int) start; i < start + count + 1; i++) {
                float mult = (range + 1);
                float val = (float) (Math.random() * mult);
                yVals1.add(new BarEntry(i + 1f, val));
            }
        }


        BarDataSet set1;

        if (mChart2.getData() != null &&
                mChart2.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart2.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart2.getData().notifyDataChanged();
            mChart2.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "机床开关机时间统计");//设置项
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mChart2.setData(data);
        }
    }

    private void initView() {


        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项

        for (String ma : jichuangNober) {
            list.add(ma);
        }

        mySpinner = (Spinner) findViewById(R.id.Spinner_city);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
                mac.setText(adapter.getItem(arg2));
                mac_no = adapter.getItem(arg2);

                initData();//网络请求更新******************

                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                mac.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });


        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项

        for (String ma : jichuangNober2) {
            list2.add(ma);
        }

        mySpinner2 = (Spinner) findViewById(R.id.Spinner_scx);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner2.setAdapter(adapter2);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
                scx.setText(adapter2.getItem(arg2));
                scx_no = adapter2.getItem(arg2);

                initData();//网络请求更新******************

                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                scx.setText("01");
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner2.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner2.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });


        gongzuotongji = (Button) findViewById(R.id.gongzuotongji);
        baojinlv = (Button) findViewById(R.id.baojinlv);
        jichuandongtaitongji = (Button) findViewById(R.id.jichuandongtaitongji);
        jichuangtingjiyy = (Button) findViewById(R.id.jichuangtingjiyy);
        jichuangkaiguansji = (Button) findViewById(R.id.jichuangkaiguansji);


        richanliang = (TextView) findViewById(R.id.richanliang);
        buhegelv = (TextView) findViewById(R.id.buhegelv);
        wanchenlv = (TextView) findViewById(R.id.wanchenlv);
        dantinenghao = (TextView) findViewById(R.id.dantinenghao);
        kaidonglv = (TextView) findViewById(R.id.kaidonglv);
        gongjianhao = (TextView) findViewById(R.id.gongjianhao);
        oee = (TextView) findViewById(R.id.oee);

        xxdata = (TextView) findViewById(R.id.xxdata);
        xxdata.setOnClickListener(this);

        mac = (TextView) findViewById(R.id.mac);
        scx = (TextView) findViewById(R.id.scx);
        date = (TextView) findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        gongzuotongji.setOnClickListener(this);
        baojinlv.setOnClickListener(this);
        jichuandongtaitongji.setOnClickListener(this);
        jichuangtingjiyy.setOnClickListener(this);
        jichuangkaiguansji.setOnClickListener(this);
    }

    private void initPri() {

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(mTfLight);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);//圆圈的颜色

        mChart.setTransparentCircleColor(Color.WHITE);//圈外颜色
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setOnChartValueSelectedListener(this);
        mChart.animateXY(1400, 1400);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);


        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        // entry label styling
        mChart.setEntryLabelColor(Color.BLACK);//项的颜色
        mChart.setCenterTextColor(Color.BLACK);
        mChart.setCenterTextSize(17);
        mChart.setCenterText("工件统计");//设置中间显示的字符
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
        mChart.setNoDataTextColor(Color.BLACK);
        setData(5, 1);
    }

    public void initData() {


        // 先从缓存中读取数据并展示
//        String cache = CacheUtils.getCache(CaseActivity.this, GlobalContants.DATA_URL);
//        if (!TextUtils.isEmpty(cache)) {
//            processData(cache);//解析数据
//        }

        getDataFromNet2();// 从网络获取数据
    }


    /**
     * 从服务器获取版本信息进行校验 方式1  HttpURLConnection + JSONObject
     */
    private void getDataFromNet2() {

        // 启动子线程异步加载数据
        new Thread() {

            @Override
            public void run() {
                Message msg = Message.obtain();
                HttpURLConnection conn = null;
                try {
//                    URL url = new URL(GlobalContants.DATA_URL);//原来的URL

                    //数据 例如：mac_no="M06-1"   date="2016-08-28"
                    URL url = new URL(GlobalContants.NEI_GET + "?mac_no=" + mac_no + "&date=" + date_no);
//                    URL url = new URL(GlobalContants.DATA);
                    // Log.d("main",GlobalContants.SECLIE_GET+"?mac_no="+mac_no+"&date="+date_no);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");// 设置请求方法
                    conn.setConnectTimeout(7000);// 设置连接超时
                    conn.setReadTimeout(8000);// 设置响应超时, 连接上了,但服务器迟迟不给响应
                    conn.connect();// 连接服务器

                    int responseCode = conn.getResponseCode();// 获取响应码
                    Log.d("main", "服务器中加载数据了,响应码=" + responseCode);
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String result = StreamUtils.readFromStream(inputStream);
                        System.out.println("网络返回:" + result);
                        // 解析json
                        webData data = processData(result);
                        msg.what = 3;
                        msg.obj = data;
                    }
                } catch (MalformedURLException e) {
                    // url错误的异常
                    msg.what = CODE_URL_ERROR;
                    e.printStackTrace();
                } catch (IOException e) {
                    // 网络错误异常
                    msg.what = CODE_NET_ERROR;
                    e.printStackTrace();
                } finally {
                    mHandler.sendMessage(msg);
                    if (conn != null) {
                        conn.disconnect();// 关闭网络连接
                    }
                }
            }
        }.start();
    }


    /**
     * 解析数据
     */
    private webData processData(String result) {
        Gson gson = new Gson();
        mData = gson.fromJson(result, webData.class);


        Log.i("main", mData.getDailyoutput() + "!!!!!!!!!!!!");

//        richanliang.setText(mData.getDailyoutput()+"");
//        buhegelv.setText(mData.getUnproducts()+"");//不合格品
//        wanchenlv.setText(mData.getDprate()+"");
//        dantinenghao.setText(mData.getEnergy()+"");
//        kaidonglv.setText(mData.getStartrate()+"");
//        oee.setText(mData.getOee()+"");
//        gongjianhao.setText(mData.getWorkpiecenumber()+"/"+mData.getSingletact());

        Log.d(TAG, "新闻中心数据: " + mData);

        System.out.println("新闻中心数据" + mData);
        return mData;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();


        switch (biao) {
            case 0:
                // the chart.
                if (mData == null) {
                    Log.i(TAG, "setData: mData == null");
                } else {
                    for (int i = 0; i < mData.getmWorkpieceData().size(); i++) {//工件统计
                        entries.add(new PieEntry((float) (
                                (Double.parseDouble(mData.getmWorkpieceData().get(i).getData()) * mult)
                                        + mult / 5), mData.getmWorkpieceData().get(i).getKey()));
                    }
                    mChart.setCenterText("工件统计");//设置中间显示的字符
                }
                break;
            case 1:
                if (mData == null) {
                    Log.i(TAG, "setData: getmDowntimeData == null");
                } else {
                    for (int i = 0; i < mData.getmDlarmData().size(); i++) {//报警统计
                        entries.add(new PieEntry((float) (
                                (Integer.parseInt(mData.getmDlarmData().get(i).getData()) * mult)
                                        + mult / 5), mData.getmDlarmData().get(i).getKey()));
                    }
                    mChart.setCenterText("报警统计");//设置中间显示的字符
                }
                break;
            case 2:
                if (mData == null) {
                    Log.i(TAG, "setData: getmMachineStatusData == null");
                } else {
                    for (int i = 0; i < mData.getmMachineStatusData().size(); i++) {//机床状态统计
                        entries.add(new PieEntry((float) (
                                (Integer.parseInt(mData.getmMachineStatusData().get(i).getData()) * mult)
                                        + mult / 5), mData.getmMachineStatusData().get(i).getKey()));
                    }
                    mChart.setCenterText("机床状态统计");//设置中间显示的字符
                }
                break;
            case 3:
                if (mData == null) {
                    Log.i(TAG, "setData: getmSwitchTime == null");
                } else {
                    for (int i = 0; i < mData.getmDowntimeData().size(); i++) {//停机原因
                        entries.add(new PieEntry((float) (
                                (Integer.parseInt(mData.getmDowntimeData().get(i).getData()) * mult) + mult / 5),
                                mData.getmDowntimeData().get(i).getKey()));
                    }
                    mChart.setCenterText("停机原因统计");//设置中间显示的字符
                }
                break;
            case 4:
                if (mData == null) {
                    Log.i(TAG, "setData: getmWorkpieceData == null");
                } else {
                    for (int i = 0; i < mData.getmWorkpieceData().size(); i++) {//工件统计
                        entries.add(new PieEntry((float) (
                                (Integer.parseInt(mData.getmWorkpieceData().get(i).getData()) * mult)
                                        + mult / 5), mData.getmWorkpieceData().get(i).getKey()));
                    }
                    mChart.setCenterText("工件统计");//设置中间显示的字符
                }
                break;
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onClick(View v) {
        initData();//网络请求更新

        //Log.d("main",GlobalContants.SECLIE_GET+"?mac_no="+mac_no+"&date="+date_no);
        switch (v.getId()) {
            case R.id.gongzuotongji:
                biao = 0;
                mChart.setVisibility(View.VISIBLE);
                mChart2.setVisibility(View.GONE);
                initPri();
                break;
            case R.id.baojinlv:
                biao = 1;
                mChart.setVisibility(View.VISIBLE);
                mChart2.setVisibility(View.GONE);
                initPri();
                break;
            case R.id.jichuandongtaitongji:
                biao = 2;
                mChart.setVisibility(View.VISIBLE);
                mChart2.setVisibility(View.GONE);
                initPri();
                break;
            case R.id.jichuangtingjiyy:
                biao = 3;
                mChart.setVisibility(View.VISIBLE);
                mChart2.setVisibility(View.GONE);
                initPri();
                break;
            case R.id.jichuangkaiguansji:
                biao = 4;
                mChart.setVisibility(View.GONE);
                mChart2.setVisibility(View.VISIBLE);
                initPri2();
                break;
            case R.id.xxdata:
                startActivity(new Intent(CaseActivity.this, Xiangxi.class));
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Case Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.xxmassdeveloper.mpchartexample/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Case Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.xxmassdeveloper.mpchartexample/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public void showDate() {
        cal = Calendar.getInstance();
        // 构建一个 DatePickerDialog 并显示
        new DatePickerDialog(CaseActivity.this,
                listener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {  //
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            cal.set(Calendar.YEAR, arg1);
            cal.set(Calendar.MONTH, arg2);
            cal.set(Calendar.DAY_OF_MONTH, arg3);
            updateDate();
        }
    };

    // 当 DatePickerDialog 关闭，更新日期显示
    private void updateDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(df.format(cal.getTime()));

        date_no = df.format(cal.getTime());//更新时间
        initData();//网络请求更新
    }


}
