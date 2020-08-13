package com.example.voyage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class list_place_adapter extends RecyclerView.Adapter<list_place_adapter.ViewHolder> {
    List<details_place> placedata;
    LayoutInflater inflater;

    list_place_adapter(Context c,List<details_place> placedata){
        this.inflater = LayoutInflater.from(c);
        this.placedata = placedata;

    }

    @NonNull
    @Override
    public list_place_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_place_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_place_adapter.ViewHolder holder, int position) {
        holder.tvPName.setText(placedata.get(position).getPname());
        holder.tvPPrice.setText(placedata.get(position).getPprice());
        holder.tvPPLocation.setText(placedata.get(position).getPlocation());
        holder.placeIV.setImageResource(placedata.get(position).getPimage());
    }



    @Override
    public int getItemCount() {
        return placedata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView placeIV;
        TextView tvPName, tvPPLocation,tvPPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeIV = itemView.findViewById(R.id.iVPlace);
            tvPName = itemView.findViewById(R.id.tvPlaceName);
            tvPPLocation = itemView.findViewById(R.id.tvPlaceLocation);
            tvPPrice = itemView.findViewById(R.id.tvPlacePrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
