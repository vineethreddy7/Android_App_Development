package com.example.teststudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    private ArrayList<Courses> coursedata = new ArrayList<Courses>();
    private LayoutInflater layoutInflater;

    public listAdapter(Context context,ArrayList<Courses> coursedata) {
        this.coursedata = coursedata;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return coursedata.size();
    }

    @Override
    public Object getItem(int i) {
        return coursedata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.listrow, null);

            holder.nameLv = view.findViewById(R.id.lvName);
            holder.feesLv = view.findViewById(R.id.lvFees);
            holder.hoursLv = view.findViewById(R.id.lvHours);

            view.setTag(holder);
        }
        else
            holder=(ViewHolder) view.getTag();
        holder.nameLv.setText(coursedata.get(i).getName());
        holder.feesLv.setText(String.format("%.2f",coursedata.get(i).getFees()));
        holder.hoursLv.setText(String.format("%.2f",coursedata.get(i).getHours()));


        return view;
    }
    static class ViewHolder
    {
        private TextView nameLv;
        private TextView feesLv;
        private TextView hoursLv;
    }
}
