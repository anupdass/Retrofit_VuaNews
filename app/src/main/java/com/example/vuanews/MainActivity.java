package com.example.vuanews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vuanews.response.Article;
import com.example.vuanews.response.NewsResponse;
import com.example.vuanews.response.Source;
import com.example.vuanews.response.WebserviceApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Adapter adapter;
    private LinearLayout liLayout;

    public static final String BASE_URL= "https://newsapi.org/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        liLayout = findViewById(R.id.linerLayout);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        String apikey = getResources().getString(R.string.apikey);
        String endUrl = String.format("everything?q=bitcoin&from=%s&sortBy=publishedAt&apiKey=%s",currentDateandTime,apikey);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        WebserviceApi webserviceApi = retrofit.create(WebserviceApi.class);

        webserviceApi.getResponse(endUrl).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.VISIBLE);
                NewsResponse newsResponse = response.body();
                List<Article> articles = newsResponse.getArticles();
                adapter = new Adapter(MainActivity.this,R.layout.item,articles);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });

    }

}