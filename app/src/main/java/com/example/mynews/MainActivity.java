package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText etQuery;
    final String API_KEY = "137f224f13fc4a378781e89cd87f98c5";
    final String orderby="publishedAt";
    Adapter adapter;
    ArrayList<Articles> articles = new ArrayList<>();
    String category;
    String country;
    Button btnSearch;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);
        setContentView(R.layout.activity_main);

        etQuery = findViewById(R.id.query);
        btnSearch = findViewById(R.id.search);
        swipeRefreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.list_container);
        Intent intent = getIntent();
        category=intent.getStringExtra("category");
        country = "in";

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

          @Override
          public void onRefresh() {
             retrieveJson("",country,API_KEY);
          }
      });
        retrieveJson("",country,API_KEY);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etQuery.getText().toString().equals(""))
                {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                        @Override
                        public void onRefresh() {
                            retrieveJson("",country,API_KEY);
                        }
                    });
                    retrieveJson("",country,API_KEY);
                }
                else
                {
                    query=etQuery.getText().toString();
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                            @Override
                            public void onRefresh() {
                                retrieveJson(query,country,API_KEY);
                            }
                        });
                        retrieveJson(query,country,API_KEY);
                    etQuery.setText("");
                }
            }
        });
    }
    public void retrieveJson(String query,String country, String apiKey)
    {
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
        if(etQuery.getText().toString().equals(""))
        call=APIClient.getInstance().getApi().getHeadlines(country,apiKey,category,orderby);
        else
        call=APIClient.getInstance().getApi().getEverything(query,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null)
                {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();//purana wala clear
                    articles=response.body().getArticles();
                    Adapter adapter= new Adapter(MainActivity.this,  articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}