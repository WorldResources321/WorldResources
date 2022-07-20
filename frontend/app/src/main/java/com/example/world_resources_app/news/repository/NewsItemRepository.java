package com.example.world_resources_app.news.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.world_resources_app.news.async.DeleteAllNotesAsyncTask;
import com.example.world_resources_app.news.async.DeleteNoteAsyncTask;
import com.example.world_resources_app.news.async.InsertNoteAsyncTask;
import com.example.world_resources_app.news.data.local.ArticleDao;
import com.example.world_resources_app.news.data.local.NewsRoomDatabase;
//import com.example.world_resources_app.news.data.remote.NewsApiClient;
import com.example.world_resources_app.news.models.NewsItem;

import java.util.List;

public class NewsItemRepository {

    private ArticleDao mArticleDao;
    private LiveData<List<NewsItem>> mAllSavedArticles;

    private static NewsItemRepository instance;
    //private NewsApiClient mNewsApiClient;

    public static NewsItemRepository getInstance() {
        if (instance == null) {
            instance = new NewsItemRepository();
        }
        return instance;
    }

    public NewsItemRepository() {
        //NewsApiClient mNewsApiClient;
        //mNewsApiClient = NewsApiClient.getInstance();
        //unused 
    }

    public NewsItemRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        mArticleDao = db.articleDao();
        mAllSavedArticles = mArticleDao.getAllSavedArticles();
    }

    public LiveData<List<NewsItem>> getAllSavedArticles() {
        return mAllSavedArticles;
    }

    public void insert(NewsItem newsItem) {
        new InsertNoteAsyncTask(mArticleDao).execute(newsItem);
    }

    public void delete(NewsItem newsItem) {
        new DeleteNoteAsyncTask(mArticleDao).execute(newsItem);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(mArticleDao).execute();
    }
}
