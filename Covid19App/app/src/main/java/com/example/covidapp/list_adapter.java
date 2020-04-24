package com.example.covidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class list_adapter extends BaseAdapter {

    private ArrayList<Cases> casedata = new ArrayList<Cases>();
    private LayoutInflater layoutInflater;

    public list_adapter(Context context, ArrayList<Cases> casedata) {
        this.casedata = casedata;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return casedata.size();
    }

    @Override
    public Object getItem(int i) {
        return casedata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_row, null);

            holder.tvLv1 = view.findViewById(R.id.lvTv1);
            holder.tvLv2 = view.findViewById(R.id.lvTv2);
            view.setTag(holder);
        }
        else
            holder=(ViewHolder) view.getTag();
        holder.tvLv1.setText(casedata.get(i).getCountry());
        holder.tvLv2.setText(String.format("%d",casedata.get(i).getAllCases()));
            return view;
        }


    static class ViewHolder
    {
        private TextView tvLv1;
        private TextView tvLv2;
    }
}
