package com.example.voyage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class list_offer_adapter extends RecyclerView.Adapter<list_offer_adapter.ViewHolder> {
    List<Offering> offerdata;
    LayoutInflater inflater;


    list_offer_adapter(Context c, List<Offering> offerdata){
        this.inflater = LayoutInflater.from(c);
        this.offerdata = offerdata;

    }

    @NonNull
    @Override
    public list_offer_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_offers,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_offer_adapter.ViewHolder holder, int position) {
        String image = offerdata.get(position).getPhoto();
        holder.offernametv.setText(offerdata.get(position).getName());
        holder.offerprivetv.setText(String.valueOf(offerdata.get(position).getPrice()));
        //holder.tvPPrice.setText("9.99");

        byte[] decstring = Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmapres = BitmapFactory.decodeByteArray(decstring,0,decstring.length);
        holder.offersiv.setImageBitmap(bitmapres);
    }



    @Override
    public int getItemCount() {
        return offerdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView offersiv;
        TextView offernametv,offerprivetv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             offersiv = itemView.findViewById(R.id.ivOffers);
            offernametv = itemView.findViewById(R.id.ivOffername);
            offerprivetv = itemView.findViewById(R.id.ivOfferPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
