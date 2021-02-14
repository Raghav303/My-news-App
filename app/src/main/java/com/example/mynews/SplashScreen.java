package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        Thread.sleep(2500);
                        Intent intent= new Intent(SplashScreen.this,Category_Chooser.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}