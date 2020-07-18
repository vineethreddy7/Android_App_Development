package com.example.notemaking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class list_note_adapter extends RecyclerView.Adapter<list_note_adapter.ViewHolder> {
   // ImageView iV;
    List<Notes> note;
    LayoutInflater inflater;


    list_note_adapter(Context context,List<Notes> note){
        this.inflater = LayoutInflater.from(context);
        this.note = note;
    }

    @NonNull
    @Override
    public list_note_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_listview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_note_adapter.ViewHolder holder, int i) {
        String name = note.get(i).getName();
        String date = note.get(i).getDate();
        String time = note.get(i).getTime();
        String img = note.get(i).getImg();
        Long id = note.get(i).getId();
        holder.tvTitle.setText(name);
        holder.tvDate.setText(date);
        holder.tvTime.setText(time);
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.iV.setImageBitmap(bitmapResult);
        Log.d("testid",""+id);
    }

    @Override
    public int getItemCount() {
        return note.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle,tvDate,tvTime;
        ImageView iV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleTV);
            tvDate = itemView.findViewById(R.id.dateTV);
            tvTime = itemView.findViewById(R.id.timeTV);
            iV = itemView.findViewById((R.id.noteIV));

            itemView.setOnClickListener(this);
        }
        public Bitmap convertToBitmap(String base64String) {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return bitmapResult;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(),NoteDetails.class);
            i.putExtra("id",note.get(getAdapterPosition()).getId());
            view.getContext().startActivity(i);
        }
    }
}
