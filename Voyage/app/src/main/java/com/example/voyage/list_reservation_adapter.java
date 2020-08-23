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

public class list_reservation_adapter extends RecyclerView.Adapter<list_reservation_adapter.ViewHolder> {

    List<Booking> booking;
    LayoutInflater inflater;


    list_reservation_adapter(Context context,List<Booking> booking){
        this.inflater = LayoutInflater.from(context);
        this.booking = booking;
    }


    @NonNull
    @Override
    public list_reservation_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_reservations,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_reservation_adapter.ViewHolder holder, int i) {
        String temail = booking.get(i).getTravelleremail();
        long id = booking.get(i).getId();
        String date = booking.get(i).getDate();
        holder.temailrtv.setText(temail);
        holder.oidtv.setText(String.valueOf(id));
        holder.rdatetv.setText(date);

    }

    @Override
    public int getItemCount() {
        return booking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView temailrtv, oidtv, rdatetv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temailrtv = itemView.findViewById(R.id.tvreservationname);
            oidtv = itemView.findViewById(R.id.tvofferid);
            rdatetv = itemView.findViewById(R.id.tvDateR);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
