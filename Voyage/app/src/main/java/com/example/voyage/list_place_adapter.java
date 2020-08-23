package com.example.voyage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class list_place_adapter extends RecyclerView.Adapter<list_place_adapter.ViewHolder> {
    List<Offering> placedata;
    LayoutInflater inflater;
    String name = "";

    list_place_adapter(Context c,List<Offering> placedata){
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
        String image = placedata.get(position).getPhoto();
        holder.tvPName.setText(placedata.get(position).getName());
       holder.tvPPrice.setText(String.valueOf(placedata.get(position).getPrice()));
        //holder.tvPPrice.setText("9.99");

        holder.tvPPLocation.setText(placedata.get(position).getPlace());
        byte[] decstring = Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmapres = BitmapFactory.decodeByteArray(decstring,0,decstring.length);
        holder.placeIV.setImageBitmap(bitmapres);
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
            Intent i = new Intent(view.getContext(),bookingpage.class);
            i.putExtra("name",tvPName.getText().toString());
            Log.d("Name1 is ",""+tvPName.getText().toString());
            view.getContext().startActivity(i);

        }
    }
}
