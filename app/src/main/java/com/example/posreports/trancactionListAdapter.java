package com.example.posreports;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posreports.Model.TransactionModel;

import java.util.List;


public class trancactionListAdapter extends BaseAdapter {

    private Context context;
    List<TransactionModel> itemsList;

    public trancactionListAdapter(Context context, List<TransactionModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;

    }

    public trancactionListAdapter() {

    }

    public void setItemsList(List<TransactionModel> itemsList) {
        this.itemsList = itemsList;

    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView date, time,price,avlQty,trancNo,trancType,itemNo,itemName,qty,price_t,totalBT,user;//,i9,i10,i11,i12,i13,i14;//, price
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.item_trans_row4, null);


        holder.date = (TextView) view.findViewById(R.id.date);
        holder.time = (TextView) view.findViewById(R.id.time);
        holder.price = (TextView) view.findViewById(R.id.price);
        holder.trancNo = (TextView) view.findViewById(R.id.trancNo);
        holder.trancType = (TextView) view.findViewById(R.id.trancType);
        holder.itemNo = (TextView) view.findViewById(R.id.itemNo);
        holder.itemName = (TextView) view.findViewById(R.id.itemName);
        holder.qty = (TextView) view.findViewById(R.id.qty);
        holder.price_t = (TextView) view.findViewById(R.id.price);
        holder.totalBT = (TextView) view.findViewById(R.id.totalBT);
        holder.user = (TextView) view.findViewById(R.id.user);
//        holder.i9 = (TextView) view.findViewById(R.id.i9);
//        holder.i10 = (TextView) view.findViewById(R.id.i10);
//        holder.i11 = (TextView) view.findViewById(R.id.i11);
//        holder.i12 = (TextView) view.findViewById(R.id.i12);
//        holder.i13 = (TextView) view.findViewById(R.id.i13);
//        holder.i14 = (TextView) view.findViewById(R.id.i14);

//        holder.itemCode.setText("" + itemsList.get(i).getItemOCode());
//        holder.itemName.setText("" + itemsList.get(i).getItemNameA());
//        holder.price.setText("" + itemsList.get(i).getF_D());
//        holder.avlQty.setText("" + itemsList.get(i).getAVLQTY());
//
//        holder.i1.setText("" + itemsList.get(i).getITEMUNIT());
//        holder.i2.setText("" + itemsList.get(i).getITEMGROUP());
//        holder.i3.setText("" + itemsList.get(i).getITEMCOLOR());
//        holder.i4.setText("" + itemsList.get(i).getITEMSIZE());
//        holder.i5.setText("" + itemsList.get(i).getITEMMODEL());
//        holder.i6.setText("" + itemsList.get(i).getITEMGS());
//        holder.i7.setText("" + itemsList.get(i).getITEMDIV());
//        holder.i8.setText("" + itemsList.get(i).getITEMSUB1());
//        holder.i9.setText("" + itemsList.get(i).getITEMSUB2());
//        holder.i10.setText("" + itemsList.get(i).getITEMSUB3());
//        holder.i11.setText("" + itemsList.get(i).getITEMSUB4());
//        holder.i12.setText("" + itemsList.get(i).getITEMSUB5());
//        holder.i13.setText("" + itemsList.get(i).getITEMSUB6());
//        holder.i14.setText("" + itemsList.get(i).getITEMSUB7());

        return view;
    }




}

