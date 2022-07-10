package com.example.world_resources_app.news.async;

import android.os.AsyncTask;

import com.example.world_resources_app.news.data.local.ArticleDao;

public class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {

    private ArticleDao mAsyncTaskDao;

    public DeleteAllNotesAsyncTask(ArticleDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mAsyncTaskDao.deleteAllSavedArticles();
        return null;
    }
}
