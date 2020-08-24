package com.example.voyage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class list_mybooking_adapter extends RecyclerView.Adapter<list_mybooking_adapter.ViewHolder> {

    List<Booking> booking;
    LayoutInflater inflater;
    DataBase db;


    list_mybooking_adapter(Context context,List<Booking> booking,DataBase db){
        this.inflater = LayoutInflater.from(context);
        this.booking = booking;
        this.db = db;
    }


    @NonNull
    @Override
    public list_mybooking_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_mybookings,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_mybooking_adapter.ViewHolder holder, int i) {
        String temail = booking.get(i).getTravelleremail();
        long id = booking.get(i).getId();
        long oid = booking.get(i).getOfferingid();
        String name = db.getO1(oid).getName();
        String date = booking.get(i).getDate();
        holder.mbnametv.setText(name);
        holder.mbbidtv.setText(String.valueOf(id));
        holder.mbdatetv.setText(date);

    }

    @Override
    public int getItemCount() {
        return booking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mbnametv,mbbidtv,mbdatetv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mbnametv = itemView.findViewById(R.id.tvmbname);
            mbbidtv = itemView.findViewById(R.id.tvmbid);
            mbdatetv = itemView.findViewById(R.id.tvmbdate);

        }


        @Override
        public void onClick(View view) {

        }
    }
}
