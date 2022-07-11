package com.example.world_resources_app.news.async;

import android.os.AsyncTask;

import com.example.world_resources_app.news.data.local.ArticleDao;
import com.example.world_resources_app.news.data.local.NewsRoomDatabase;
import com.example.world_resources_app.news.models.NewsItem;
import com.example.world_resources_app.news.models.Source;


public class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

    private final ArticleDao mDao;

    public PopulateDbAsyncTask(NewsRoomDatabase db) {
        mDao = db.articleDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        NewsItem newsItem = new NewsItem();
        newsItem.setAuthor("AuthorName");
        newsItem.setTitle("NewsTitle");
        newsItem.setUrl("imageUrl");
        newsItem.setSource(new Source("BBC"));
        newsItem.setDescription("Description");
        newsItem.setPublishedAt("2 Jan 2020");

        mDao.insert(newsItem);
        return null;
    }
}