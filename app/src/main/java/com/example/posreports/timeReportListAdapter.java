package com.example.posreports;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posreports.Model.TimeReortModel;
import com.example.posreports.Model.TransactionModel;

import java.util.List;


public class timeReportListAdapter extends BaseAdapter {

    private Context context;
    List<TimeReortModel> itemsList;

    public timeReportListAdapter(Context context, List<TimeReortModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;

    }

    public timeReportListAdapter() {

    }

    public void setItemsList(List<TimeReortModel> itemsList) {
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
        TextView posNo, action,trancT,trancD,trancNo,empName,empNo;//,i9,i10,i11,i12,i13,i14;//, price
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.item_time_row, null);


        holder.posNo = (TextView) view.findViewById(R.id.posNo);
        holder.action = (TextView) view.findViewById(R.id.action);
        holder.trancT = (TextView) view.findViewById(R.id.trancT);
//        holder.trancNo = (TextView) view.findViewById(R.id.trancNo);
        holder.trancD = (TextView) view.findViewById(R.id.trancD);
        holder.empName = (TextView) view.findViewById(R.id.empName);
        holder.empNo = (TextView) view.findViewById(R.id.empNo);

        holder.empName.setText("" + itemsList.get(i).getEmpName());
        holder.empNo.setText("" + itemsList.get(i).getEmpNo());
        holder.trancD.setText("" + itemsList.get(i).getTranceDate());
        holder.trancT.setText("" + itemsList.get(i).getTranceTime());
//        holder.trancNo.setText("" + itemsList.get(i).getEmpNo());
        holder.action.setText("" + itemsList.get(i).getAction());
        holder.posNo.setText("" + itemsList.get(i).getPosNo());

        return view;
    }




}

