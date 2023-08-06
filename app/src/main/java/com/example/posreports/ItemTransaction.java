package com.example.posreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.posreports.databinding.ActivityItemTransactionBinding;

public class ItemTransaction extends AppCompatActivity {
    public static ActivityItemTransactionBinding myBindingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_item_transaction);
        myBindingItem= DataBindingUtil.setContentView(this, R.layout.activity_item_transaction);
        //***************************

    }
}