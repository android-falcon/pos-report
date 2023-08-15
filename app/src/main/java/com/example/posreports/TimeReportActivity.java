package com.example.posreports;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.posreports.Model.EmployModel;
import com.example.posreports.Model.POSModel;
import com.example.posreports.Model.TimeReortModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeReportActivity extends AppCompatActivity {
TextView FDate,TDate,FTime,TTime,exit,show;
    EditText EName;
    ImageView search;
    CheckBox empNameC,posNoC,dateC,timeC;
    ListView listV;
    ImportJson importJson;
    timeReportListAdapter adapter;
    employListAdapter emp;
    Dialog dialog;
    Spinner posSpinner;
    ArrayAdapter posAdapter;
    String pos="1";
    List<POSModel> posListS=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_report);
//        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);

        initialization();

    }

    private void initialization() {

        importJson=new ImportJson(TimeReportActivity.this);
        FDate=findViewById(R.id.FDate);
        TDate=findViewById(R.id.TDate);
        FTime=findViewById(R.id.FTime);
        TTime=findViewById(R.id.TTime);

        EName=findViewById(R.id.EName);
        search=findViewById(R.id.search);

        empNameC=findViewById(R.id.empNameC);
        posNoC=findViewById(R.id.posNoC);
        dateC=findViewById(R.id.dateC);
        timeC=findViewById(R.id.timeC);
        listV=findViewById(R.id.list);
        exit=findViewById(R.id.exit);
        show=findViewById(R.id.show);
        posSpinner=findViewById(R.id.spinner);

        importJson.getPOSData();
        posSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e("inSelect","up");
                try {
                    switch (adapterView.getId()) {


                        case R.id.D1:
                            pos = posListS.get(0).getPOSNO();
                            Log.e("inSelect", "up => " + pos);
                            break;
                    }
                    }catch(Exception e){

                    }

                }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmployData();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fdate =FDate.getText().toString();
                String Tdate =TDate.getText().toString();
                String Ftime =FTime.getText().toString();
                String Ttime =TTime.getText().toString();
                String Ename =EName.getText().toString();

                String emp="0";
                String posNo="0";
                String date="0";
                String time="0";


                if(empNameC.isChecked()){
                    emp="1";
                }else{
                    emp="0";

                }

                if(dateC.isChecked()){
                    date="1";
                }else{
                    date="0";

                }

                if(timeC.isChecked()){
                    time="1";
                }else{
                    time="0";

                }

                if(posNoC.isChecked()){
                    posNo="1";
                }else{
                    posNo="0";

                }


                importJson.getTimeReport(Fdate,Tdate,Ftime,Ttime,Ename,date,time,emp,posNo,pos);
            }
        });

        FTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        getTime(FTime);
            }
        });

        TTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime(TTime);
            }
        });


        FDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(FDate);
            }
        });

        TDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(TDate);
            }
        });
    }


    void getDate (TextView textView){

        final Calendar c = Calendar.getInstance();
      int   mYear = c.get(Calendar.YEAR);
      int  mMonth = c.get(Calendar.MONTH);
       int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    void getEmployData(){
        importJson.getEmployData();
    }

    void getTime (TextView textView){

        final Calendar c = Calendar.getInstance();
       int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        textView.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }


   void  openSearchDialog(List<EmployModel> listEmp){

         dialog=new Dialog(TimeReportActivity.this);
       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       dialog.setContentView(R.layout.employ_dialog);
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


       ListView list=dialog.findViewById(R.id.list);
       EditText editText=dialog.findViewById(R.id.empNoName);

        emp=new employListAdapter(TimeReportActivity.this,listEmp);
       list.setAdapter(emp);

       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               if(!editText.getText().toString().equals("")){

                  List<EmployModel> temp= findSearch(listEmp,editText.getText().toString());
                   emp=new employListAdapter(TimeReportActivity.this,temp);

               }else{

                   emp=new employListAdapter(TimeReportActivity.this,listEmp);
               }
               list.setAdapter(emp);

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });


       dialog.show();

    }

    private List<EmployModel> findSearch(List<EmployModel> ListTemp,String editT) {
        List<EmployModel> tempList=new ArrayList<>();
        List<EmployModel> ListSearch=new ArrayList<>();
        ListSearch=ListTemp;


        for(int i=0;i<ListSearch.size();i++){
            if(ListSearch.get(i).getUSERNAME().contains(editT)||ListSearch.get(i).getUSERNO().contains(editT)){
                tempList.add(ListSearch.get(i));
            }
        }


        return tempList;
    }


    public void fillArrayDataAfterFilter(List<TimeReortModel> list){

        adapter = new timeReportListAdapter(TimeReportActivity.this, list);
        listV.setAdapter(adapter);
    }

    public void setTextEmploy(String data) {
        EName.setText(""+data);
        dialog.dismiss();
    }

    public void fillSpinerPOsNo(List<String> item ,List<POSModel> posList) {
        posListS=posList;
         posAdapter= new ArrayAdapter<String>(TimeReportActivity.this, R.layout.spinner_style, item);
        posSpinner.setAdapter(posAdapter);
    }
}
