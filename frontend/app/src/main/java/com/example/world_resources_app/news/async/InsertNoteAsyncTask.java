package com.example.world_resources_app.news.async;

import android.os.AsyncTask;

import com.example.world_resources_app.news.data.local.ArticleDao;
import com.example.world_resources_app.news.models.NewsItem;

public class InsertNoteAsyncTask extends AsyncTask<NewsItem, Void, Void> {

    private ArticleDao mAsyncTaskDao;

    public InsertNoteAsyncTask(ArticleDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(NewsItem... newsItems) {
        mAsyncTaskDao.insert(newsItems[0]);
        return null;
    }
}
