package com.example.posreports;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimeReportActivity extends AppCompatActivity {
TextView FDate,TDate,FTime,TTime,exit,show;
    EditText EName;
    ImageView search;
    CheckBox empNameC,posNoC,dateC,timeC;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_report);
//        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);

        initialization();

    }

    private void initialization() {

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
        list=findViewById(R.id.list);
        exit=findViewById(R.id.exit);
        show=findViewById(R.id.show);
    }

}
