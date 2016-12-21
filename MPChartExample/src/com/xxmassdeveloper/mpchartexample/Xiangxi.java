package com.xxmassdeveloper.mpchartexample;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xxmassdeveloper.mpchartexample.Bean.XiangxiBean;
import com.xxmassdeveloper.mpchartexample.Utils.CacheUtils;
import com.xxmassdeveloper.mpchartexample.Utils.StreamUtils;
import com.xxmassdeveloper.mpchartexample.global.GlobalContants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Xiangxi extends Activity {

    protected static final int CODE_NET_ERROR = 2;//网络错误
    protected static final int CODE_URL_ERROR = 1;// url错误

    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    private TextView date;
    private TextView mac;
    private TextView scx;

    private String mac_no;
    private String date_no;
    private String scx_no;

    private Calendar cal;

    private TextView weizhix;
    private TextView sudux;
    private TextView fuzaix;

    private TextView weizhiy;
    private TextView suduy;
    private TextView fuzaiy;
    private TextView weizhiz;
    private TextView suduz;
    private TextView fuzaiz;
    private TextView weizhib;
    private TextView sudub;
    private TextView fuzaib;
    private TextView weizhia;
    private TextView sudua;
    private TextView fuzaia;
    private TextView weizhiu;
    private TextView suduu;
    private TextView fuzaiu;





    String[] jichuangNober = {"M01", "MO2", "M03-1", "M03-2", "M04-1", "M04-2", "M05-1",
            "M05-2", "M06-1", "M06-2"};


    private List<String> list2 = new ArrayList<String>();
    private Spinner mySpinner2;
    private ArrayAdapter<String> adapter2;

    String[] jichuangNober2 = {"01", "O2", "03", "04",
            "05"};
    private XiangxiBean mData;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case CODE_NET_ERROR:
                    Toast.makeText(Xiangxi.this, "网络异常", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(Xiangxi.this, "url网络异常", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 4:
                    XiangxiBean mD = (XiangxiBean) msg.obj;

                    Toast.makeText(Xiangxi.this, "加载中", Toast.LENGTH_SHORT).show();

                    weizhix.setText(mD.getX000()+"\n"+mD.getX001()+"\n"+mD.getX002());
                    sudux.setText(mD.getX01()+"");
                    fuzaix.setText(mD.getX02()+"");

                    weizhiy.setText(mD.getY00()+"");
                    suduy.setText(mD.getY01()+"");
                    fuzaiy.setText(mD.getY02()+"");

                    weizhiz.setText(mD.getZ00()+"");
                    suduz.setText(mD.getZ01()+"");
                    fuzaiz.setText(mD.getZ02()+"");

                    weizhib.setText(mD.getB00()+"");
                    sudub.setText(mD.getB01()+"");
                    fuzaib.setText(mD.getB02()+"");

                    weizhia.setText(mD.getA00()+"");
                    sudua.setText(mD.getA01()+"");
                    fuzaia.setText(mD.getA02()+"");

                    weizhiu.setText(mD.getU00()+"");
                    suduu.setText(mD.getU01()+"");
                    fuzaiu.setText(mD.getU02()+"");

                    break;

                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangxi);


        mac_no = new String();
        date_no = new String();
        scx_no = new String();
        initView();
        mac_no = mac.getText().toString();
        scx_no = scx.getText().toString();
        date_no = date.getText().toString();

        new Thread(){
            @Override
            public void run() {
                super.run();
                getDataFromNet2();
                try {
                    sleep(2000);//每2秒刷新一次数据
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initView() {
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        weizhix = (TextView) findViewById(R.id.weizhix);
        sudux = (TextView) findViewById(R.id.sudux);
        fuzaix = (TextView) findViewById(R.id.fuzaix);

        weizhiy = (TextView) findViewById(R.id.weizhiy);
        suduy = (TextView) findViewById(R.id.suduy);
        fuzaiy = (TextView) findViewById(R.id.fuzaiy);

        weizhiz = (TextView) findViewById(R.id.weizhiz);
        suduz = (TextView) findViewById(R.id.suduz);
        fuzaiz = (TextView) findViewById(R.id.fuzaiz);

        weizhib = (TextView) findViewById(R.id.weizhib);
        sudub = (TextView) findViewById(R.id.sudub);
        fuzaib = (TextView) findViewById(R.id.fuzaib);

        weizhia = (TextView) findViewById(R.id.weizhia);
        sudua = (TextView) findViewById(R.id.sudua);
        fuzaia = (TextView) findViewById(R.id.fuzaia);

        weizhiu = (TextView) findViewById(R.id.weizhiu);
        suduu = (TextView) findViewById(R.id.suduu);
        fuzaiu = (TextView) findViewById(R.id.fuzaiu);



        for (String ma:jichuangNober){
            list.add(ma);
        }

        mySpinner = (Spinner)findViewById(R.id.Spinner_city);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
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
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });


        mac = (TextView) findViewById(R.id.mac);
        date = (TextView) findViewById(R.id.date);
        scx = (TextView) findViewById(R.id.scx);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });







        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项

        for (String ma:jichuangNober2){
            list2.add(ma);
        }

        mySpinner2 = (Spinner)findViewById(R.id.Spinner_scx);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list2);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner2.setAdapter(adapter2);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
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
        mySpinner2.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner2.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
    }


    public void showDate(){
        cal = Calendar.getInstance();
        // 构建一个 DatePickerDialog 并显示
        new DatePickerDialog(Xiangxi. this ,
                listener ,
                cal.get(Calendar. YEAR ),
                cal.get(Calendar. MONTH ),
                cal.get(Calendar. DAY_OF_MONTH )
        ).show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){  //
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            cal .set(Calendar. YEAR , arg1);
            cal .set(Calendar. MONTH , arg2);
            cal .set(Calendar. DAY_OF_MONTH , arg3);
            updateDate();
        }
    };

    // 当 DatePickerDialog 关闭，更新日期显示
    private void updateDate(){
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        date .setText( df .format( cal .getTime()));

        date_no = df .format( cal .getTime());//更新时间
        initData();//网络请求更新
    }





    public void initData() {
        Log.d("main", "服务器中加载数据了");

        // 先从缓存中读取数据并展示
        String cache = CacheUtils.getCache(Xiangxi.this, GlobalContants.SECLIE_GET);
        if (!TextUtils.isEmpty(cache)) {
            processData(cache);//解析数据
        }

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
                    URL url = new URL(GlobalContants.NEI_GET+"?type=cur&mac_no="+mac_no+"&date="+date_no);

                    Log.d("main",GlobalContants.NEI_GET+"?mac_no="+mac_no+"&date="+date_no);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");// 设置请求方法
                    conn.setConnectTimeout(5000);// 设置连接超时
                    conn.setReadTimeout(5000);// 设置响应超时, 连接上了,但服务器迟迟不给响应
                    conn.connect();// 连接服务器

                    int responseCode = conn.getResponseCode();// 获取响应码
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String result = StreamUtils.readFromStream(inputStream);
                        System.out.println("网络返回:" + result);
                        // 解析json
                        XiangxiBean dat = processData(result);
                        Log.i("main",dat.getA01()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        msg.what = 4;
                        msg.obj = dat;

                    }
                } catch (MalformedURLException e) {
                    // url错误的异常
                    msg.what = 1;
                    e.printStackTrace();
                } catch (IOException e) {
                    // 网络错误异常
                    msg.what = 2;
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
     * 解析数据并显示
     */
    private XiangxiBean processData(String result) {
        Gson gson = new Gson();
        mData = gson.fromJson(result, XiangxiBean.class);

        return mData;
//        Log.d(TAG, "新闻中心数据: " + mData);
//
//        System.out.println("新闻中心数据"+ mData);
    }


}
