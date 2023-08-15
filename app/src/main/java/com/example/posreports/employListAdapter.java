package com.example.posreports;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.posreports.Model.EmployModel;
import com.example.posreports.Model.TimeReortModel;

import java.util.List;


public class employListAdapter extends BaseAdapter {

    private Context context;
    List<EmployModel> itemsList;

    public employListAdapter(Context context, List<EmployModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;

    }

    public employListAdapter() {

    }

    public void setItemsList(List<EmployModel> itemsList) {
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
        TextView userNo, userName;
        TableRow table;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.item_emp_row, null);


        holder.userNo = (TextView) view.findViewById(R.id.empNo);
        holder.userName = (TextView) view.findViewById(R.id.empName);
        holder.table=view.findViewById(R.id.table);

        holder.userNo.setText("" + itemsList.get(i).getUSERNO());
        holder.userName.setText("" + itemsList.get(i).getUSERNAME());

        holder.table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeReportActivity tem=(TimeReportActivity) context;
                tem.setTextEmploy(itemsList.get(i).getUSERNAME());

            }
        });


        return view;
    }




}

