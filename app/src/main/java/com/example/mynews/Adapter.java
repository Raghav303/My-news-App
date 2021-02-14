package com.example.mynews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.http.Url;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
private Context context;
private ArrayList<Articles> articles;

    public Adapter(@NonNull Context context, ArrayList<Articles> articles) {
        this.context=context;
        this.articles=articles;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.utility,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         Articles a = articles.get(position);
        String imageUrl=a.getUrlToImage();
        Picasso.with(context).load(imageUrl).into(holder.image);
        holder.source.setText(a.getSource().getName());
        holder.date.setText(dateTime(a.getPublishedAt()));
        holder.title.setText(a.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(a.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class Holder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView date;
        ImageView image;
        TextView source;
        CardView cardView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tvTitle);
            date=(TextView)itemView.findViewById(R.id.tvDate);
            source=(TextView)itemView.findViewById(R.id.tvSource);
            image=(ImageView)itemView.findViewById(R.id.image);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }
    }
    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale("en"));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }
}
