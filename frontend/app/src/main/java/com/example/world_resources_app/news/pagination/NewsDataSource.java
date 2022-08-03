package com.example.world_resources_app.news.pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.world_resources_app.news.data.remote.NewsAPI;
import com.example.world_resources_app.news.data.remote.ServiceGeneratorUtil;
import com.example.world_resources_app.news.models.NewsItem;
import com.example.world_resources_app.news.models.RootJsonData;
import com.example.world_resources_app.news.utils.DataStatus;
import com.example.world_resources_app.news.utils.UtilsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource extends PageKeyedDataSource<Integer, NewsItem> {

    private static final int FIRST_PAGE = 1;
    public static final String SORT_ORDER = "publishedAt";
    public static final String API_KEY = UtilsUtil.API_KEY;
    public String language = "en";
    public static final int PAGE_SIZE = 100;

    private String mKeyword;
    private MutableLiveData<DataStatus> dataStatusMutableLiveData;

    public NewsDataSource(String keyword) {
        mKeyword = keyword;
        dataStatusMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DataStatus> getDataStatusMutableLiveData() {
        return dataStatusMutableLiveData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, NewsItem> callback) {
        dataStatusMutableLiveData.postValue(DataStatus.LOADING);
        Call<RootJsonData> rootJsonDataCall = createNewsJsonDataCall(mKeyword, FIRST_PAGE);
        rootJsonDataCall.enqueue(new Callback<RootJsonData>() {
            @Override
            public void onResponse(Call<RootJsonData> call, Response<RootJsonData> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().getNewsItems(), null, FIRST_PAGE + 1);
                    dataStatusMutableLiveData.postValue(DataStatus.LOADED);
                }
                if (response.body().getTotalResults() == 0) {
                    dataStatusMutableLiveData.postValue(DataStatus.EMPTY);
                }
            }

            @Override
            public void onFailure(Call<RootJsonData> call, Throwable t) {
                dataStatusMutableLiveData.postValue(DataStatus.ERROR);
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsItem> callback) {
        Call<RootJsonData> rootJsonDataCall = createNewsJsonDataCall(mKeyword, FIRST_PAGE);
        rootJsonDataCall.enqueue(new Callback<RootJsonData>() {
            @Override
            public void onResponse(Call<RootJsonData> call, Response<RootJsonData> response) {
                // if the current page is greater than one
                // we are decrementing the page number
                // else there is no previous page
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null) {
                    // passing the loaded data
                    // and the previous page key
                    callback.onResult(response.body().getNewsItems(), adjacentKey);
                }
            }

            @Override
            public void onFailure(Call<RootJsonData> call, Throwable t) {
                //do nothing
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsItem> callback) {
        Call<RootJsonData> rootJsonDataCall = createNewsJsonDataCall(mKeyword, params.key);
        rootJsonDataCall.enqueue(new Callback<RootJsonData>() {
            @Override
            public void onResponse(Call<RootJsonData> call, Response<RootJsonData> response) {
                dataStatusMutableLiveData.postValue(DataStatus.LOADED);

                if (response.code() == 429) {
                    // no more results
                    List<NewsItem> emptyList = new ArrayList<>();
                    callback.onResult(emptyList, null);
                }

                if (response.body() != null) {
                    // if the response has next page
                    // incrementing the next page number
                    Integer key = params.key + 1;

                    // passing the loaded data and next page value
                    if (!response.body().getNewsItems().isEmpty()) {
                        callback.onResult(response.body().getNewsItems(), key);
                    }
                }
            }

            @Override
            public void onFailure(Call<RootJsonData> call, Throwable t) {
                dataStatusMutableLiveData.postValue(DataStatus.ERROR);
            }
        });
    }

    private Call<RootJsonData> createNewsJsonDataCall(String keyword, int pageNumber) {

        String locale = UtilsUtil.getCountry();
        boolean isLocaleAvailable = UtilsUtil.checkLocale(locale);

        language = Locale.getDefault().getLanguage();
        boolean isLanguageAvailable = UtilsUtil.checkLanguage(language);

        NewsAPI newsAPI = ServiceGeneratorUtil.createService(NewsAPI.class);
        Call<RootJsonData> rootJsonDataCall;

        if (keyword.isEmpty()) {

            if (isLocaleAvailable) {
                rootJsonDataCall = newsAPI.getTopHeadlinesByCountry(locale, language, API_KEY, pageNumber, PAGE_SIZE);
            } else {
                if (isLanguageAvailable) {
                    language = UtilsUtil.getLanguage();
                } else {
                    language = "en";
                }

                rootJsonDataCall = newsAPI.getTopHeadlinesByLanguage(language, API_KEY, pageNumber, PAGE_SIZE);
            }
        } else {
            rootJsonDataCall = newsAPI.searchNewsByKeyWord(keyword, SORT_ORDER, language, API_KEY, pageNumber, PAGE_SIZE);

        }

        return rootJsonDataCall;
    }
}


