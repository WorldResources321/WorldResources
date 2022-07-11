package com.example.world_resources_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.world_resources_app.forum.ForumManagement;
import com.example.world_resources_app.news.NewsManagement;

public class MainActivity extends AppCompatActivity {

    private Button forumButton, newsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forumButton = findViewById(R.id.forum_button);
        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForumManagement.class);
                startActivity(intent);
            }
        });

        newsButton = findViewById(R.id.news_button);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsManagement.class);
                startActivity(intent);
            }
        });


    }

}