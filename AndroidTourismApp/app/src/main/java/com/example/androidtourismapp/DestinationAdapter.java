package com.example.androidtourismapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DestinationAdapter extends BaseAdapter {
    private ArrayList<Destination> destinationDetails;
    LayoutInflater layoutInflater;

    public DestinationAdapter(Context context, ArrayList<Destination> destinationDetails) {
        this.destinationDetails = destinationDetails;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return destinationDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return destinationDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_row,null);
            holder.tvName=view.findViewById(R.id.tvDestinationName);
            holder.tvPrice=view.findViewById(R.id.tvDestinationPrice);
            holder.destinationImage=view.findViewById(R.id.imageView2);
            view.setTag(holder);
        }
        else
            holder =(ViewHolder) view.getTag();

        holder.tvPrice.setText(String.format("%.2f",destinationDetails.get(i).getDestinationPrice()));
        holder.destinationImage.setImageResource(destinationDetails.get(i).getDestinationImage());
        holder.tvName.setText(destinationDetails.get(i).getDestinationName());
        return view;
    }
    static class ViewHolder{
        TextView tvName,tvPrice;
        ImageView destinationImage;

    }
}
