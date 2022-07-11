package com.example.world_resources_app.news;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.world_resources_app.R;
import com.example.world_resources_app.news.fragments.FavoritesFragment;
import com.example.world_resources_app.news.fragments.HeadlinesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NewsManagement extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HeadlinesFragment())
                    .setReorderingAllowed(true)
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.page_headlines:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new HeadlinesFragment())
                            .setReorderingAllowed(true)
                            .commit();
                    break;
                case R.id.page_favorites:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new FavoritesFragment())
                            .setReorderingAllowed(true)
                            .commit();
                    break;
                default:
                    break;
            }
            return true;
        });
    }
}