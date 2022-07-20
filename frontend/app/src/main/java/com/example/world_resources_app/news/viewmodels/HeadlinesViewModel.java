package com.example.world_resources_app.news.viewmodels;

import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.world_resources_app.news.models.NewsItem;
//import com.example.world_resources_app.news.pagination.news.NewsDataSource;
import com.example.world_resources_app.news.pagination.news.NewsDataSourceFactory;
import com.example.world_resources_app.news.utils.DataStatus;

public class HeadlinesViewModel extends ViewModel {

    public LiveData<PagedList<NewsItem>> itemPagedList;
   // private MutableLiveData<NewsDataSource> liveDataSource;//private MutableLiveData<HeadlinesDataSource> liveDataSource;
    private NewsDataSourceFactory newsDataSourceFactory;//HeadlinesDataSourceFactory newsDataSourceFactory;
    private LiveData dataStatus;

    public HeadlinesViewModel() {

        //MutableLiveData<NewsDataSource> liveDataSource;

        newsDataSourceFactory = new NewsDataSourceFactory();//HeadlinesDataSourceFactory();
       // liveDataSource = newsDataSourceFactory.getNewsLiveDataSource();
        dataStatus = newsDataSourceFactory.getDataStatusLiveData();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(100).build();

        itemPagedList = (new LivePagedListBuilder(newsDataSourceFactory, pagedListConfig)).build();
    }

    public void setCategory(String category) {
        //newsDataSourceFactory.setCategory(category);
        newsDataSourceFactory.setQuery(category);
        refreshData();
    }

    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    public LiveData<DataStatus> getDataStatus() {
        return dataStatus;
    }
}
