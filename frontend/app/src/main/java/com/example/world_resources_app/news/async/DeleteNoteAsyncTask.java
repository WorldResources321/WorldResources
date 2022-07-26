package com.example.world_resources_app.news.async;

import android.os.AsyncTask;

import com.example.world_resources_app.news.data.local.ArticleDao;
import com.example.world_resources_app.news.models.NewsItem;


public class DeleteNoteAsyncTask extends AsyncTask<NewsItem, Void, Void> {

    private ArticleDao myAsyncTaskDao;

    public DeleteNoteAsyncTask(ArticleDao noteDao) {
        myAsyncTaskDao = noteDao;
    }

    @Override
    protected Void doInBackground(final NewsItem... newsItems) {
        myAsyncTaskDao.deleteArticle(newsItems[0]);
        return null;
    }
}