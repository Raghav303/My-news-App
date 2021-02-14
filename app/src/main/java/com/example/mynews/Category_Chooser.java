package com.example.mynews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class Category_Chooser extends AppCompatActivity {
CardView all,sports,buisness,entertainment,health,technology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);
        setContentView(R.layout.activity_category__chooser);

        all =(CardView) findViewById(R.id.headlines);
        sports=(CardView)findViewById(R.id.sports);
        buisness=(CardView)findViewById(R.id.buisness);
        entertainment=(CardView)findViewById(R.id.enetertainment);
        health=(CardView)findViewById(R.id.health);
        technology=(CardView)findViewById(R.id.technology);
        Log.e("hi","1");
       all.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.e("hi","1");
               Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
               intent.putExtra("category","general");
               startActivity(intent);
           }
       });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
                intent.putExtra("category","sports");
                startActivity(intent);
            }
        });

        buisness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
                intent.putExtra("category","business");
                startActivity(intent);
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
                intent.putExtra("category","entertainment");
                startActivity(intent);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
                intent.putExtra("category","health");
                startActivity(intent);
            }
        });

        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category_Chooser.this,MainActivity.class);
                intent.putExtra("category","technology");
                startActivity(intent);
            }
        });


    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)//alertbox k asspass k area pe tap karne se bhi  band ahi hoga
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertdialog = builder.create();
        alertdialog.show();
        Button b1 = alertdialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button b2 = alertdialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if(b1 != null && b2!=null) {
            b1.setTextColor(getResources().getColor(R.color.white));
            b2.setTextColor(getResources().getColor(R.color.white));
        }
    }
}