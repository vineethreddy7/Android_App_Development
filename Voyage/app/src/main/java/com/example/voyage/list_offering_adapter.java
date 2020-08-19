package com.example.voyage;

import android.content.Context;
import android.content.Intent;
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

public class list_offering_adapter extends RecyclerView.Adapter<list_offering_adapter.ViewHolder> {

    List<Offering> offering;
    LayoutInflater inflater;

    list_offering_adapter(Context context,List<Offering> offering){
        this.inflater = LayoutInflater.from(context);
        this.offering = offering;
    }


    @NonNull
    @Override
    public list_offering_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_host_offerings,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_offering_adapter.ViewHolder holder, int i) {
        String name = offering.get(i).getName();
        String price = String.valueOf(offering.get(i).getPrice());
        String place = offering.get(i).getPlace();
        String image = offering.get(i).getPhoto();
        holder.Hostplacenametv.setText(name);
        holder.Hostplacepricetv.setText(price);
        holder.Hostplacetv.setText(place);
        byte[] decodedstring  = Base64.decode(image,Base64.DEFAULT);
        Bitmap res = BitmapFactory.decodeByteArray(decodedstring,0,decodedstring.length);
        holder.hostplacesIv.setImageBitmap(res);

    }

    @Override
    public int getItemCount() {
        return offering.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hostplacesIv;
        TextView Hostplacenametv, Hostplacepricetv, Hostplacetv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostplacesIv = itemView.findViewById(R.id.ivHostplaces);
            Hostplacenametv = itemView.findViewById(R.id.tvHostplacename);
            Hostplacepricetv = itemView.findViewById(R.id.tvHostplaceprice);
            Hostplacetv = itemView.findViewById(R.id.tvHostplacedate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(),Hostplacedetails.class);
            view.getContext().startActivity(i);
        }
    }
}
