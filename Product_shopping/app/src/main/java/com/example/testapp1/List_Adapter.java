package com.example.testapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class List_Adapter extends BaseAdapter {
    private ArrayList<products> productData;
    private LayoutInflater layoutInflater;

    public List_Adapter(Context context,ArrayList<products> productData) {
        this.productData = productData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productData.size();
    }

    @Override
    public Object getItem(int i) {
        return productData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_row,null);

            holder.nameTv = view.findViewById(R.id.tv1Name);
            holder.priceTv = view.findViewById((R.id.tv1Price));
            holder.img = view.findViewById(R.id.tv1Img);
            view.setTag(holder);
        }
        else
            holder=(ViewHolder) view.getTag();
        holder.nameTv.setText(productData.get(i).getName());
        holder.priceTv.setText(String.format("%.2f",productData.get(i).getPrice()));
        holder.img.setImageResource(productData.get(i).getImage());


        return view;
    }
    static class ViewHolder{
        private TextView nameTv ;
        private TextView priceTv;
        private ImageView img;
    }

}
