package com.example.notemaking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class list_category_adapter extends BaseAdapter {
    private ArrayList<Notes> notedata = new ArrayList<Notes>();
    private LayoutInflater layoutInflater;

    public list_category_adapter(Context context,ArrayList<Notes> notedata){
        this.notedata = notedata;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return notedata.size();
    }

    @Override
    public Object getItem(int i) {
        return notedata.get(i);
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
            view = layoutInflater.inflate(R.layout.list_category_row, null);

            holder.tvCLR = view.findViewById(R.id.tvLR);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.tvCLR.setText(notedata.get(i).getSubject());

        return view;


    }

    static class ViewHolder{
        private TextView tvCLR;
    }
}
