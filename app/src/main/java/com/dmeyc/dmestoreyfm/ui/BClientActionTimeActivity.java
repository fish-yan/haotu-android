package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.AccountTimePicker;
import com.dmeyc.dmestoreyfm.wedgit.PickerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BClientActionTimeActivity extends BaseActivity implements OnDateSelectedListener {
//    @Bind(R.id.tv_actiontime)
//    ItemView tv_actiontime;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.et_duration)
    EditText et_duration;
    @Bind(R.id.cb_GUing)
    CheckBox cb_GUing;
    @Bind(R.id.cb_linshic)
    CheckBox cb_linshic;

   @Bind(R.id.ll_activionproperty)
    LinearLayout ll_activionproperty;
   @Bind(R.id.ll_calander)
   LinearLayout ll_calander;
   @Bind(R.id.ll_binesstime)
   LinearLayout ll_binesstime;
   @Bind(R.id.tv_bintime)
   TextView tv_bintime;
   @Bind(R.id.tv_selectcalander)
   TextView tv_selectcalander;
   @Bind(R.id.ll_choiceclub)
   LinearLayout ll_choiceclub;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientactiontime;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if("chanlange".equals(getIntent().getStringExtra("actiontype"))){
            ll_activionproperty.setVisibility(View.GONE);
        }else {
            if("3".equals(getIntent().getStringExtra("groupty"))){
                ll_activionproperty.setVisibility(View.GONE);
            }else if("5".equals(getIntent().getStringExtra("groupty"))){
                ll_calander.setVisibility(View.VISIBLE);
                ll_binesstime.setVisibility(View.VISIBLE);
                ll_activionproperty.setVisibility(View.GONE);
                ll_choiceclub.setVisibility(View.GONE);
                checttype=2;
            }else {
                ll_activionproperty.setVisibility(View.VISIBLE);
            }
        }
    }
   public int checttype=1;
    @OnClick({R.id.tv_actiontime,R.id.tv_right_title_bar,R.id.cb_GUing,R.id.cb_linshic,R.id.ll_calander,R.id.ll_binesstime})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_actiontime){
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(viewid==R.id.tv_right_title_bar){
            if("chanlange".equals(getIntent().getStringExtra("actiontype"))){
                if(TextUtils.isEmpty(tv_time.getText().toString().trim())){
                    ToastUtil.show("请选择时间");
                    return;
                }
            }else {
        if("5".equals(getIntent().getStringExtra("groupty"))){
            if(TextUtils.isEmpty(tv_bintime.getText().toString())){
                ToastUtil.show("请选择营业时间");
                return;
            }
      for (int i=0;i<calendarDays1.size();i++){
        String   date=calendarDays1.get(i).getYear()+"-"+(calendarDays1.get(i).getMonth()+1)+"-"+calendarDays1.get(i).getDay()+" "+tv_bintime.getText().toString()+":00";
        if(i==calendarDays1.size()-1){
            selectdates=selectdates+date;
            }else {
            selectdates=selectdates+date+",";
           }
            }
            if(TextUtils.isEmpty(selectdates)){
                ToastUtil.show("请选择日期");
                return;
                  }

      }else {
            if(TextUtils.isEmpty(tv_time.getText().toString().trim())){
                ToastUtil.show("请选择时间");
                return;
            }
                }
            }

            if(TextUtils.isEmpty(et_duration.getText().toString().trim())){
                ToastUtil.show("请填写时长");
                return;
            }
            Intent intent=new Intent();
            intent.putExtra("starttime",tv_time.getText().toString().trim());
            intent.putExtra("duration",et_duration.getText().toString().trim());
            intent.putExtra("chectype",checttype);
            intent.putExtra("calanderselect",selectdates);
            setResult(234,intent);
            finish();
        }else if(R.id.cb_GUing==viewid){
            checttype=1;
            cb_GUing.setChecked(true);
            cb_linshic.setChecked(false);
        }else if(R.id.cb_linshic==viewid){
            checttype=2;
            cb_GUing.setChecked(false);
            cb_linshic.setChecked(true);
        }else if(R.id.ll_calander==viewid){
            goShop();
        }else if(R.id.ll_binesstime==viewid){
            getbinisstiem();
          }
    }

    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择开始时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        dialog.setMessageFormat("yyyy-MM-dd HH");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                tv_time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
//                Toast.makeText(PublishActivity.this,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }



    Dialog dialog;
    private TextView tv_subt;
    public void goShop(){
        dialog  = new Dialog(BClientActionTimeActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_calander);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        MaterialCalendarView widget = (MaterialCalendarView) dialog.findViewById(R.id.calendarView);
//        textView = (TextView) dialog.findViewById(R.id.textView);
        tv_subt=dialog.findViewById(R.id.tv_subt);
        tv_subt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                tv_selectcalander.setText("已选择");
            }
        });
        widget.setOnDateChangedListener(this);
        dialog.show();
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//        textView.setText(FORMATTER.format(date.getDate()));
    }
    String selectdates="";
    List<CalendarDay> calendarDays1;
    @Override
    public void selectedData(List<CalendarDay> calendarDays) {
        calendarDays1  =calendarDays;
//       for (int i=0;i<calendarDays.size();i++){
//        String   date=calendarDays.get(i).getYear()+"-"+(calendarDays.get(i).getMonth()+1)+"-"+calendarDays.get(i).getDay()+" "+"00:00:00";
//        if(i==calendarDays.size()-1){
//            selectdates=selectdates+date;
//        }else {
//            selectdates=selectdates+date+",";
//        }
//
//       }
    }

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    Dialog dialosg;
    AccountTimePicker pv_year;
    AccountTimePicker pv_mone;
    TextView tv_outlog;
    String checkkyear="";
    String chexday="";
    String year;
    public void getbinisstiem(){
        dialosg  = new Dialog(BClientActionTimeActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_tiemselect);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.6
        window.setAttributes(params);


        pv_year = (AccountTimePicker) dialog.findViewById(R.id.pv_year);
//        pv_mone=(AccountTimePicker)dialog.findViewById(R.id.pv_mone);
        tv_outlog=dialog.findViewById(R.id.tv_outlog);
        List<String> data = new ArrayList<String>();
//        List<String> mones = new ArrayList<String>();
//        for (int i = 0; i < 24; i++)
            for (int i = 0; i < 24; i++)
            {
                data.add(i < 10 ? "0" + i+":00" : "" + i+":00");
            }
//        for (int i = 1; i < 13; i++)
//        {
//            mones.add(i +"");
//        }
//        pv_mone.setData(mones);
        pv_year.setData(data);
        pv_year.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                year=text;
            }
        });
//        pv_mone.setOnSelectListener(new PickerView.onSelectListener()
//        {
//
//            @Override
//            public void onSelect(String text)
//            {
//                mone=text;
//            }
//        });
        dialog.show();
        tv_outlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                tv_bintime.setText(year);
//                getData();
//                billListAdapter.notifyDataSetChanged();
            }
        });
    }


}
