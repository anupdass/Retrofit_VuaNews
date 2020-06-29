package com.example.vuanews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vuanews.response.Article;
import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    private TextView textView,title;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView1);
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.titlertext);

        Article article = (Article) getIntent().getSerializableExtra("article");
        if(article != null){
            String imgUrl = article.getUrlToImage();
            Picasso.get().load(imgUrl).into(imageView);
            textView.setText(article.getDescription());
            title.setText(article.getPublishedAt());
        }

    }
}