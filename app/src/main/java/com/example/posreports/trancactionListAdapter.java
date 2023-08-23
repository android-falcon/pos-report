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
        TextView date, time,price,avlQty,trancNo,trancType,itemNo,itemName,qty,price_t,totalBT,user,priceAT;//,i9,i10,i11,i12,i13,i14;//, price
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
//        holder.price_t = (TextView) view.findViewById(R.id.price);
        holder.totalBT = (TextView) view.findViewById(R.id.totalBT);
        holder.priceAT=view.findViewById(R.id.priceAT);
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
        holder.date.setText("" + itemsList.get(i).getVHFDATE());
        holder.itemName.setText("" + itemsList.get(i).getITEMONAMEA());
        holder.time.setText("" + itemsList.get(i).getVHFTIM());
        holder.price.setText("" + itemsList.get(i).getPRICE());
        holder.trancNo.setText("" + itemsList.get(i).getVHFI());
        holder.trancType.setText("" + itemsList.get(i).getTRANSKIND());
        holder.itemNo.setText("" + itemsList.get(i).getITEMOCODE());
        holder.qty.setText("" + itemsList.get(i).getPQTY());
//        holder.price_t.setText("" + itemsList.get(i).getPRICEKIND());
        holder.totalBT.setText("" + itemsList.get(i).getNETBEFORDIS());
        holder.priceAT.setText("" + itemsList.get(i).getNETAFTERDIS());
        holder.user.setText("" + itemsList.get(i).getUSERNO());


        return view;
    }




}

