package com.example.posreports;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.posreports.Model.POSModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class dailyActiveReport extends AppCompatActivity {

    TextView FDate, TDate ,payIn ,payOut ,show;
    Spinner spinner;
    CheckBox dateC,posNoC;
    ListView list;
    ImportJson importJson;
    List<POSModel> posListS=new ArrayList<>();
    ArrayAdapter<String> posAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialy_report_activity);
//        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);

        initialization();

    }

    private void initialization() {

        importJson=new ImportJson(dailyActiveReport.this);
        FDate=findViewById(R.id.FDate);
        TDate=findViewById(R.id.TDate);
        spinner=findViewById(R.id.spinner);
        show=findViewById(R.id.show);
        dateC=findViewById(R.id.dateC);
        payIn=findViewById(R.id.payIn);
        payOut=findViewById(R.id.payOut);
        posNoC=findViewById(R.id.posNoC);
        list=findViewById(R.id.list);

        importJson.getPOSData();


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


    public void fillSpinerPOsNo(List<String> posNameList, List<POSModel> item) {
        posListS=item;
        posAdapter = new ArrayAdapter<String>(dailyActiveReport.this, R.layout.spinner_style, posNameList);
        spinner.setAdapter(posAdapter);
    }

}
