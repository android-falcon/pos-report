package com.example.posreports;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    MaterialCardView soldqtyrepCard, grouprepCard, cashrepCard, mostsalesepCard,reservationCard;
    ConstraintLayout ipSettings_layout;

    public final static String IP_SETTINGS = "IP_SETTINGS";
    public final static String IP_PREF = "IP_Address";
    public final static String CONO_PREF = "Company_No";
    public final static String VHFTYPE = "VHFTYPE";
    private JSONArray arrayReservation;
    private String ipAddress, coNo;
    GeneralMethod generalMethod;
    int  mHour, mMinute;
//    ImportData importData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ipSettings_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("showSettingsDialog","9999999"+VHFTYPE);
                showSettingsDialog();


            }
        });

        if (!checkIpSettings())
            showSettingsDialog();

    }

    int taxFlag=0;
    private void showSettingsDialog() {

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ip_settings_dialog);

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = getResources().getDisplayMetrics().widthPixels;
//        dialog.getWindow().setAttributes(lp);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Log.e("showSettingsDialog","101010");

        dialog.show();

        TextInputLayout textInputIpAddress, textInputCoNo;
        EditText ipEdt, coNoEdt;
        Button submitBtn;


        RadioButton include,execlude;
        textInputIpAddress = dialog.findViewById(R.id.textInputIpAddress);
        textInputCoNo = dialog.findViewById(R.id.textInputCoNo);
        ipEdt = dialog.findViewById(R.id.ipEdt);
        coNoEdt = dialog.findViewById(R.id.coNoEdt);
        submitBtn = dialog.findViewById(R.id.submitBtn);
        RadioGroup vhfTypeRadioGroup = (RadioGroup) dialog.findViewById(R.id.vhfTypeRadioGroup);
        RadioButton execlude_radio =  dialog.findViewById(R.id.execlude_radio);
        RadioButton include_radio =  dialog.findViewById(R.id.include_radio);
        vhfTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup,
                                         int radioButtonID) {
                switch(radioButtonID) {
                    case R.id.include_radio:
                        taxFlag=0;
                        break;
                    case R.id.execlude_radio:
                        taxFlag=1;
                        break;

                }
            }
        });
        SharedPreferences sharedPref = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);

        ipEdt.setText(sharedPref.getString(IP_PREF, ""));
        coNoEdt.setText(sharedPref.getString(CONO_PREF, ""));
        Log.e("sharedPref","VHFTYPE=="+sharedPref.getInt(VHFTYPE,0));
        if(sharedPref.getInt(VHFTYPE,0)==0){// 0== include
            include_radio.setChecked(true);
        }else execlude_radio.setChecked(true);//1==execlude

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ipAddress = ipEdt.getText().toString().trim();
                String coNo = coNoEdt.getText().toString().trim();
                 int checked=vhfTypeRadioGroup.getCheckedRadioButtonId();
                 if(checked==R.id.execlude_radio)
                 {
                     taxFlag=1;
                 }else taxFlag=0;
                 Log.e("getCheckedRadioButtonId","==="+taxFlag);
                if (!ipAddress.equals("")) {

                    if (!coNo.equals("")) {

                        SharedPreferences.Editor editor = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE).edit();
                        editor.putString(IP_PREF, ipAddress);
                        editor.putString(CONO_PREF, coNo);
                        editor.putInt(VHFTYPE,taxFlag);
                        editor.apply();
//                        SectionClient.Instance=null;
                        dialog.dismiss();

                    } else {

                        textInputCoNo.setError("*Required");


                    }

                } else {

                    textInputIpAddress.setError("*Required");

                }


            }
        });

        ipEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                textInputIpAddress.setError(null);

            }
        });

        coNoEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                textInputCoNo.setError(null);

            }
        });


    }

    private boolean checkIpSettings() {

        SharedPreferences sharedPref = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);
        ipAddress = sharedPref.getString(IP_PREF, "");
        coNo = sharedPref.getString(CONO_PREF, "");

        Log.e("IP_PREF", ipAddress + "");
        Log.e("CONO_PREF", coNo);

        return !(ipAddress + "").trim().equals("") &&
                !(coNo + "").trim().equals("");

    }


    void init() {
        generalMethod = new GeneralMethod(MainActivity.this, MainActivity.this);
        generalMethod.setWindow();
        soldqtyrepCard = findViewById(R.id.soldqtyrepCard);
        grouprepCard = findViewById(R.id.grouprepCard);
        cashrepCard = findViewById(R.id.cashrepCard);
        mostsalesepCard = findViewById(R.id.mostsalesepCard);
        ipSettings_layout = findViewById(R.id.ipSettings_layout);
        reservationCard= findViewById(R.id.reservationCard);
        reservationCard.setOnClickListener(onClickListener);
        soldqtyrepCard.setOnClickListener(onClickListener);
        grouprepCard.setOnClickListener(onClickListener);
        cashrepCard.setOnClickListener(onClickListener);
        mostsalesepCard.setOnClickListener(onClickListener);



    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.soldqtyrepCard:
                    Intent intent1 = new Intent(MainActivity.this, ItemTransaction.class);
                    startActivity(intent1);
                    break;
//
//                case R.id.grouprepCard:
//                    Intent intent2 = new Intent(MainActivity.this, GroupReport.class);
//                    startActivity(intent2);
//                    break;
//
//                case R.id.cashrepCard:
//                    Intent intent3 = new Intent(MainActivity.this, CashReport.class);
//                    startActivity(intent3);
//                    break;
//
//                case R.id.mostsalesepCard:
//                    Intent intent4 = new Intent(MainActivity.this, MostRecentSalesReport.class);
//                    startActivity(intent4);
//                    break;
//                case R.id.reservationCard:
//                    openDialogReservation();
//                    break;

            }

        }

    };


}