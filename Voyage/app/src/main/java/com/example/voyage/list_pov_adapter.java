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

public class list_pov_adapter extends RecyclerView.Adapter<list_pov_adapter.ViewHolder> {

    List<Booking> booking;
    LayoutInflater inflater;
    DataBase db;


    list_pov_adapter(Context context,List<Booking> booking,DataBase db){
        this.inflater = LayoutInflater.from(context);
        this.booking = booking;
        this.db = db;
    }


    @NonNull
    @Override
    public list_pov_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_pov,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_pov_adapter.ViewHolder holder, int i) {
        long id = booking.get(i).getId();
        String email = booking.get(i).getTravelleremail();
        long oid = booking.get(i).getOfferingid();
        String tfname = db.getTraveller(email).getFname();
       String tlname = db.getTraveller(email).getLname();
        String name = db.getO1(oid).getName();
        String date = booking.get(i).getDate();
        holder.povpnametv.setText(name);//name
        holder.povdatetv.setText(date);
        holder.povnametv.setText(tfname+" "+tlname);

    }

    @Override
    public int getItemCount() {
        return booking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView povpnametv,povdatetv,povnametv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            povpnametv = itemView.findViewById(R.id.tvpovpname);
            povdatetv = itemView.findViewById(R.id.tvpovdate);
            povnametv = itemView.findViewById(R.id.tvpovname);

        }


        @Override
        public void onClick(View view) {

        }
    }
}
