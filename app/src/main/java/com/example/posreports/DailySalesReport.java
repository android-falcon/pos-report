package com.example.posreports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

//import com.example.posreports.databinding.ActivityDailySalesReportBinding;


public class DailySalesReport extends AppCompatActivity {
//    public static ActivityDailySalesReportBinding myBindingItem;
    String fromD="",toDate="",posNo="";
//    GeneralMethod generalMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        generalMethod=new GeneralMethod(this,DailySalesReport.this);
        setContentView(R.layout.activity_daily_sales_report);
//        myBindingItem.fromdate.setOnClickListener(v->{
//            generalMethod.DateClick( myBindingItem.fromdate);
//        });
//        myBindingItem.todate.setOnClickListener(v->{
//            generalMethod.DateClick( myBindingItem.todate);
//        });
//        myBindingItem.Preivew.setOnClickListener(v->{
//            fromD=myBindingItem.fromdate.getText().toString();
//            toDate=myBindingItem.todate.getText().toString();
//            posNo=myBindingItem.PosNO.getSelectedItem().toString();
//            Log.e("Preivew",""+posNo+"\t"+fromD+"\t"+toDate);
//            getData(fromD,toDate,posNo);
//        });


    }

    private void getData(String fromD, String toDate, String posNo) {
    }
}