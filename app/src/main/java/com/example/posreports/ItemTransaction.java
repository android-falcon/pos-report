package com.example.posreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.posreports.Model.GetCaptionModel;
import com.example.posreports.Model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

//import com.example.posreports.databinding.ActivityItemTransactionBinding;

public class ItemTransaction extends AppCompatActivity {
//    public static ActivityItemTransactionBinding myBindingItem;
    TextView FD,TD;
    EditText ITEM_NO;
    Spinner SEASONS,STYELS_1,USERS,SALESS,POSYS,SHELFS,COLORS,STYLES_2,BYS,COLORS_2,LENGTHS,ZONES,SDS,CLASSS,GS,BS,SIS;
    RadioButton all,salesButton,refoundButton;
    CheckBox itemBox,itemPBox,price;
    Button ShowB;
    List<GetCaptionModel> AllData;
    ListView tranceList;
    trancactionListAdapter adapter;
ImportJson importJson;
    Spinner Desc1, Desc2, Desc3, Desc4, Desc5, Desc6, Desc7, Desc8, Desc9,
            Desc10, Desc11, Desc12, Desc13, Desc14;
    TextView N1, N2, N3, N4, N5, N6, N7, N8, N9,
            N10, N11, N12, N13, N14;
    EditText itemNo;

    String DS1="", DS2="", DS3="", DS4="", DS5="", DS6="", DS7="", DS8="", DS9="", DS10="",
            DS11="", DS12="", DS13="", DS14="",ITEMOCODEs="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_trans_report);
//        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);

        initialization();

    }

    private void initialization() {
        FD=findViewById(R.id.FD);
        TD=findViewById(R.id.TD);
        ITEM_NO=findViewById(R.id.ITEM_NO);
        SEASONS=findViewById(R.id.SEASONS);
        STYELS_1=findViewById(R.id.STYELS_1);
        USERS=findViewById(R.id.USERS);
        SALESS=findViewById(R.id.SALESS);
        POSYS=findViewById(R.id.POSYS);
        SHELFS=findViewById(R.id.SHELFS);
        COLORS=findViewById(R.id.COLORS);
        STYLES_2=findViewById(R.id.STYLES_2);
        BYS=findViewById(R.id.BYS);
        COLORS_2=findViewById(R.id.COLORS_2);
        LENGTHS=findViewById(R.id.LENGTHS);
        ZONES=findViewById(R.id.ZONES);
        SDS=findViewById(R.id.SDS);
        CLASSS=findViewById(R.id.CLASSS);
        GS=findViewById(R.id.GS);
        SIS=findViewById(R.id.SIS);

        all=findViewById(R.id.all);
        salesButton=findViewById(R.id.salesButton);
        refoundButton=findViewById(R.id.refoundButton);

        itemBox=findViewById(R.id.itemBox);
        itemPBox=findViewById(R.id.itemPBox);
        price=findViewById(R.id.price);
        tranceList=findViewById(R.id.list);


        Desc1  = findViewById(R.id.D1);
        Desc2  = findViewById(R.id.D2);
        Desc3  = findViewById(R.id.D3);
        Desc4  = findViewById(R.id.D4);
        Desc5  = findViewById(R.id.D5);
        Desc6  = findViewById(R.id.D6);
        Desc7  = findViewById(R.id.D7);
        Desc8  = findViewById(R.id.D8);
        Desc9  = findViewById(R.id.D9);
        Desc10 = findViewById(R.id.D10);
        Desc11 = findViewById(R.id.D11);
        Desc12 = findViewById(R.id.D12);
        Desc13 = findViewById(R.id.D13);
        Desc14 = findViewById(R.id.D14);


        N1  = findViewById(R.id.N1);
        N2  = findViewById(R.id.N2);
        N3  = findViewById(R.id.N3);
        N4  = findViewById(R.id.N4);
        N5  = findViewById(R.id.N5);
        N6  = findViewById(R.id.N6);
        N7  = findViewById(R.id.N7);
        N8  = findViewById(R.id.N8);
        N9  = findViewById(R.id.N9);
        N10 = findViewById(R.id.N10);
        N11 = findViewById(R.id.N11);
        N12 = findViewById(R.id.N12);
        N13 = findViewById(R.id.N13);
        N14 = findViewById(R.id.N14);

        itemNo=findViewById(R.id.ItemCodeEditTextTag);


        importJson = new ImportJson(ItemTransaction.this);

        ShowB=findViewById(R.id.ShowB);

        AllData=new ArrayList<>();
        importJson.getFilter();
        Desc1  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc2  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc3  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc4  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc5  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc6  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc7  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc8  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc9  .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc10 .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc11 .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc12 .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc13 .setOnItemSelectedListener(new OnItemSelectedCodepage());
        Desc14 .setOnItemSelectedListener(new OnItemSelectedCodepage());


        ShowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

importJson.getData();

            }
        });

    }

    private class OnItemSelectedCodepage implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3)
        {

            Log.e("inSelect","up");
            try {
            switch (arg0.getId()){


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
            }catch (Exception e){
                Log.e("inSelect", "error => " +e.toString());
        }

        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0)
        {
            // TODO Auto-generated method stub
        }
    }

    void  fillAdapter(List<GetCaptionModel> list){

        if(list.size()!=0) {
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


                ArrayAdapter Apd1= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(0).getDescList());
                Desc1.setAdapter(Apd1);
                ArrayAdapter Apd2= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(1).getDescList());
                Desc2.setAdapter(Apd2);
                ArrayAdapter Apd3= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(2).getDescList());
                Desc3.setAdapter(Apd3);
                ArrayAdapter Apd4= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(3).getDescList());
                Desc4.setAdapter(Apd4);
                ArrayAdapter Apd5= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(4).getDescList());
                Desc5.setAdapter(Apd5);
                ArrayAdapter Apd6= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(5).getDescList());
                Desc6.setAdapter(Apd6);
                ArrayAdapter Apd7= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(6).getDescList());
                Desc7.setAdapter(Apd7);
                ArrayAdapter Apd8= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(7).getDescList());
                Desc8.setAdapter(Apd8);
                ArrayAdapter Apd9= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(8).getDescList());
                Desc9.setAdapter(Apd9);
                ArrayAdapter Apd10= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(9).getDescList());
                Desc10.setAdapter(Apd10);
                ArrayAdapter Apd11= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(10).getDescList());
                Desc11.setAdapter(Apd11);
                ArrayAdapter Apd12= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(11).getDescList());
                Desc12.setAdapter(Apd12);
                ArrayAdapter Apd13= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(12).getDescList());
                Desc13.setAdapter(Apd13);
                ArrayAdapter Apd14= new ArrayAdapter<String>(ItemTransaction.this, R.layout.spinner_style, list.get(13).getDescList());
                Desc14.setAdapter(Apd14);



            } catch (Exception e) {

            }


        }


    }

    public void  fillArrayDataAfterFilter(List<TransactionModel> captionItemInfos){

        adapter = new trancactionListAdapter(ItemTransaction.this, captionItemInfos);
        tranceList.setAdapter(adapter);
    }
}