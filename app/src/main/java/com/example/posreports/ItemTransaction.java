package com.example.posreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.posreports.Model.CaptionItemInfo;
import com.example.posreports.Model.GetCaptionModel;
import com.example.posreports.Model.POSModel;
import com.example.posreports.Model.SalesManModel;
import com.example.posreports.Model.TransactionModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import com.example.posreports.databinding.ActivityItemTransactionBinding;

public class ItemTransaction extends AppCompatActivity {
    //    public static ActivityItemTransactionBinding myBindingItem;
    ArrayAdapter<String> posAdapter;
    List<POSModel> posListS=new ArrayList<>();
    TextView FD, TD;
    EditText ITEM_NO;
    Spinner SEASONS, STYELS_1, USERS, SALESS, POSYS, SHELFS, COLORS, STYLES_2, BYS, COLORS_2, LENGTHS, ZONES, SDS, CLASSS, GS, BS, SIS;
    RadioButton all, salesButton, refoundButton;
    CheckBox itemBox, itemPBox, price;
    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14,divPrice;
    TextView ShowB, exit;
    List<GetCaptionModel> AllData;
    ListView tranceList;
    trancactionListAdapter adapter;
    ImportJson importJson;
    Spinner Desc1, Desc2, Desc3, Desc4, Desc5, Desc6, Desc7, Desc8, Desc9,
            Desc10, Desc11, Desc12, Desc13, Desc14,posSpinner,SALESSpinner;
    TextView N1, N2, N3, N4, N5, N6, N7, N8, N9,
            N10, N11, N12, N13, N14;
    EditText itemNo,FP,TP;

    CaptionListAdapter adapters;
    ListView capList;
    String DS1 = "", DS2 = "", DS3 = "", DS4 = "", DS5 = "", DS6 = "", DS7 = "", DS8 = "", DS9 = "", DS10 = "",
            DS11 = "", DS12 = "", DS13 = "", DS14 = "", ITEMOCODEs = "";
    List<SalesManModel> sales;
    TextView TBD,AllDis,TAD,pus,mad,sum,tax,TWOD,total;
    List<TransactionModel>itemTranceList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_trans_report);
//        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);

        initialization();

    }

    private void initialization() {
        sales=new ArrayList<>();
        FD = findViewById(R.id.FD);
        TD = findViewById(R.id.TD);
        ITEM_NO = findViewById(R.id.ITEM_NO);
        SEASONS = findViewById(R.id.SEASONS);
        STYELS_1 = findViewById(R.id.STYELS_1);
        USERS = findViewById(R.id.USERS);
        SALESS = findViewById(R.id.SALESS);
        POSYS = findViewById(R.id.POSYS);
        SHELFS = findViewById(R.id.SHELFS);
        COLORS = findViewById(R.id.COLORS);
        STYLES_2 = findViewById(R.id.STYLES_2);
        BYS = findViewById(R.id.BYS);
        COLORS_2 = findViewById(R.id.COLORS_2);
        LENGTHS = findViewById(R.id.LENGTHS);
        ZONES = findViewById(R.id.ZONES);
        SDS = findViewById(R.id.SDS);
        CLASSS = findViewById(R.id.CLASSS);
        GS = findViewById(R.id.GS);
        SIS = findViewById(R.id.SIS);
        SALESSpinner=findViewById(R.id.SALESS);

        all = findViewById(R.id.all);
        salesButton = findViewById(R.id.salesButton);
        refoundButton = findViewById(R.id.refoundButton);
        FP = findViewById(R.id.FP);
        TP = findViewById(R.id.TP);
        posSpinner=findViewById(R.id.POSYS);

//        itemBox=findViewById(R.id.itemBox);
//        itemPBox=findViewById(R.id.itemPBox);
        //price=findViewById(R.id.price);
        tranceList = findViewById(R.id.list);
        capList = findViewById(R.id.capList);
        divPrice=findViewById(R.id.divPrice);
        Desc1 = findViewById(R.id.D1);
        Desc2 = findViewById(R.id.D2);
        Desc3 = findViewById(R.id.D3);
        Desc4 = findViewById(R.id.D4);
        Desc5 = findViewById(R.id.D5);
        Desc6 = findViewById(R.id.D6);
        Desc7 = findViewById(R.id.D7);
        Desc8 = findViewById(R.id.D8);
        Desc9 = findViewById(R.id.D9);
        Desc10 = findViewById(R.id.D10);
        Desc11 = findViewById(R.id.D11);
        Desc12 = findViewById(R.id.D12);
        Desc13 = findViewById(R.id.D13);
        Desc14 = findViewById(R.id.D14);


        N1 = findViewById(R.id.N1);
        N2 = findViewById(R.id.N2);
        N3 = findViewById(R.id.N3);
        N4 = findViewById(R.id.N4);
        N5 = findViewById(R.id.N5);
        N6 = findViewById(R.id.N6);
        N7 = findViewById(R.id.N7);
        N8 = findViewById(R.id.N8);
        N9 = findViewById(R.id.N9);
        N10 = findViewById(R.id.N10);
        N11 = findViewById(R.id.N11);
        N12 = findViewById(R.id.N12);
        N13 = findViewById(R.id.N13);
        N14 = findViewById(R.id.N14);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);

        itemNo = findViewById(R.id.ItemCodeEditTextTag);
        TBD=findViewById(R.id.TBD);
        AllDis=findViewById(R.id.AllDis);
        TAD=findViewById(R.id.TAD);
        pus=findViewById(R.id.pus);
        mad=findViewById(R.id.mad);
        sum=findViewById(R.id.sum);
        tax=findViewById(R.id.tax);
        TWOD=findViewById(R.id.TWOD);
        total=findViewById(R.id.total);
        price=findViewById(R.id.price);


        importJson = new ImportJson(ItemTransaction.this);
        importJson.getSalesMan();
        ShowB = findViewById(R.id.show);
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        importJson.getPOSData();

        AllData = new ArrayList<>();
        importJson.getFilter();
        Desc1.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc2.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc3.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc4.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc5.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc6.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc7.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc8.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc9.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc10.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc11.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc12.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc13.setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc14.setOnItemSelectedListener(new OnItemSelectedCodepage());

        FD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(FD);
            }
        });

        TD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(TD);
            }
        });

        ShowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!ITEM_NO.getText().toString().equals("")) {
                    ITEMOCODEs=ITEM_NO.getText().toString();
                }else {
                    ITEMOCODEs="";
                }

                String Skind="",iKind="0",bGr="0",cC1="0",cC2="0",cC3="0",cC4="0",cC5="0",cC6="0",cC7="0",pr="0",divP="0";

                if(salesButton.isChecked()){
                    iKind="1";
                    Skind="Sales";
                }else if(refoundButton.isChecked()){
                    iKind="2";
                    Skind="refound";
                }else {
                    iKind="0";
                    Skind="All";
                }

                if(c1.isChecked()){
                   bGr="1";
                }else {
                    bGr="0";
                }

                if(c8.isChecked()){
                    cC1="1";
                }

                if(c9.isChecked()){
                    cC2="1";
                }

                if(c10.isChecked()){
                    cC3="1";
                }


                if(c11.isChecked()){
                    cC4="1";
                }


                if(c12.isChecked()){
                    cC5="1";
                }


                if(c13.isChecked()){
                    cC6="1";
                }

                if(c14.isChecked()){
                    cC7="1";
                }

                if(price.isChecked()){
                    pr="1";
                }
                if(divPrice.isChecked()){
                    divP="1";
                }


                if(!FP.getText().toString().equals("")){
                    if(!TP.getText().toString().equals("")) {
                        importJson.getData(FD.getText().toString(), TD.getText().toString(), iKind, bGr, cC1, cC2, cC3, cC4, cC5, cC6, cC7, DS1, DS8, DS9, DS10, DS11, DS12, DS13, DS14, "USERNO", "" +
                                "", DS4, Skind, ITEMOCODEs, "", DS3, "", DS7, "", pr, FP.getText().toString(),TP.getText().toString(),divP,"","","","","","");
                    }else {
                        TP.setError("Required!");
                    }
                }else {
                    FP.setError("Required!");
                }


//                if(!ITEM_NO.getText().toString().equals("")) {
//                    ITEMOCODEs=ITEM_NO.getText().toString();
//                }else {
//                    ITEMOCODEs="";
//                }
//                importJson.getDataCap(DS4,DS1,DS2,DS3,DS5,DS6,DS7,DS8,DS9,DS10,DS11,DS12,DS13,DS14,ITEMOCODEs);

            }
        });

    }

    public void fillSpinerPOsNo(List<String> posNameList, List<POSModel> item) {
        posListS=item;
       posAdapter = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, posNameList);
        posSpinner.setAdapter(posAdapter);
    }

    private class OnItemSelectedCodepage implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            Log.e("inSelect", "up");
            try {
                switch (arg0.getId()) {


                    case R.id.D1:
                        DS1 = AllData.get(0).getDescList().get(arg2).toString();
                        Log.e("inSelect", "up => " + DS1);
                        break;
                    case R.id.D2:
                        try {
                            DS2 = AllData.get(1).getDescList().get(arg2).toString();
                        } catch (Exception e) {
                        }
                        Log.e("inSelect", "up2 => " + DS2);
                        break;
                    case R.id.D3:
                        DS3 = AllData.get(2).getDescList().get(arg2).toString();
                        Log.e("inSelect", "up3 => " + DS3);

                        break;
                    case R.id.D4:
                        DS4 = AllData.get(3).getDescList().get(arg2).toString();

                        break;
                    case R.id.D5:
                        DS5 = AllData.get(4).getDescList().get(arg2).toString();

                        break;
                    case R.id.D6:
                        DS6 = AllData.get(5).getDescList().get(arg2).toString();

                        break;
                    case R.id.D7:
                        DS7 = AllData.get(6).getDescList().get(arg2).toString();

                        break;
                    case R.id.D8:

                        DS8 = AllData.get(7).getDescList().get(arg2).toString();

                        break;
                    case R.id.D9:
                        DS9 = AllData.get(8).getDescList().get(arg2).toString();

                        break;
                    case R.id.D10:
                        DS10 = AllData.get(9).getDescList().get(arg2).toString();

                        break;
                    case R.id.D11:
                        DS11 = AllData.get(10).getDescList().get(arg2).toString();

                        break;
                    case R.id.D12:
                        DS12 = AllData.get(11).getDescList().get(arg2).toString();

                        break;
                    case R.id.D13:
                        DS13 = AllData.get(12).getDescList().get(arg2).toString();

                        break;
                    case R.id.D14:
                        DS14 = AllData.get(13).getDescList().get(arg2).toString();

                        break;


                }
            } catch (Exception e) {
                Log.e("inSelect", "error => " + e.toString());
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    void fillAdapter(List<GetCaptionModel> list) {

        if (list.size() != 0) {
            try {


                N1.setText("" + list.get(0).getDESCNAMEA());
                N2.setText("" + list.get(1).getDESCNAMEA());
                N3.setText("" + list.get(2).getDESCNAMEA());
                N4.setText("" + list.get(3).getDESCNAMEA());
                N5.setText("" + list.get(4).getDESCNAMEA());
                N6.setText("" + list.get(5).getDESCNAMEA());
                N7.setText("" + list.get(6).getDESCNAMEA());
                N8.setText("" + list.get(7).getDESCNAMEA());
                N9.setText("" + list.get(8).getDESCNAMEA());
                N10.setText("" + list.get(9).getDESCNAMEA());
                N11.setText("" + list.get(10).getDESCNAMEA());
                N12.setText("" + list.get(11).getDESCNAMEA());
                N13.setText("" + list.get(12).getDESCNAMEA());
                N14.setText("" + list.get(13).getDESCNAMEA());


                ArrayAdapter Apd1 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(0).getDescList());
                Desc1.setAdapter(Apd1);
                ArrayAdapter Apd2 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(1).getDescList());
                Desc2.setAdapter(Apd2);
                ArrayAdapter Apd3 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(2).getDescList());
                Desc3.setAdapter(Apd3);
                ArrayAdapter Apd4 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(3).getDescList());
                Desc4.setAdapter(Apd4);
                ArrayAdapter Apd5 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(4).getDescList());
                Desc5.setAdapter(Apd5);
                ArrayAdapter Apd6 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(5).getDescList());
                Desc6.setAdapter(Apd6);
                ArrayAdapter Apd7 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(6).getDescList());
                Desc7.setAdapter(Apd7);
                ArrayAdapter Apd8 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(7).getDescList());
                Desc8.setAdapter(Apd8);
                ArrayAdapter Apd9 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(8).getDescList());
                Desc9.setAdapter(Apd9);
                ArrayAdapter Apd10 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(9).getDescList());
                Desc10.setAdapter(Apd10);
                ArrayAdapter Apd11 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(10).getDescList());
                Desc11.setAdapter(Apd11);
                ArrayAdapter Apd12 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(11).getDescList());
                Desc12.setAdapter(Apd12);
                ArrayAdapter Apd13 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(12).getDescList());
                Desc13.setAdapter(Apd13);
                ArrayAdapter Apd14 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(13).getDescList());
                Desc14.setAdapter(Apd14);


            } catch (Exception e) {

            }


        }


    }

    public void fillArrayDataAfterFilter(List<TransactionModel> captionItemInfos) {

        itemTranceList=captionItemInfos;
        adapter = new trancactionListAdapter(ItemTransaction.this, captionItemInfos);
        tranceList.setAdapter(adapter);
        calculate();
    }


    public void fillArrayDataAfterFillters(List<CaptionItemInfo> captionItemInfos) {

        adapters = new CaptionListAdapter(ItemTransaction.this, captionItemInfos);
        capList.setAdapter(adapters);
    }


    public void fillSalesFillters(List<String> list,List<SalesManModel>listSales) {

        sales=listSales;
        ArrayAdapter Apd1 = new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list);
        SALESSpinner.setAdapter(Apd1);
    }

    public void calculate(){

        double bt=0;
        double at=0;
        double total1=0;
        double allDic=0,tax1=0,totalW=0;

        for(int i=0;i<itemTranceList.size();i++){

            bt+=Double.parseDouble(itemTranceList.get(i).getNETBEFORDIS());
            at+=Double.parseDouble(itemTranceList.get(i).getNETAFTERDIS());
            total1+=Double.parseDouble(itemTranceList.get(i).getTOTAL());
            allDic+=Double.parseDouble(itemTranceList.get(i).getDISCOUNT());
            tax1+=Double.parseDouble(itemTranceList.get(i).getTAXABLE());


        }

        AllDis.setText(""+allDic);
        TAD.setText(""+at);
        TBD.setText(""+bt);
        pus.setText(""+bt);
        mad.setText(""+bt);
        sum.setText("");
        tax.setText(""+tax1);
        TWOD.setText(""+totalW);
        total.setText(""+total1);




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

}