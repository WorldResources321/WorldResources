package com.example.world_resources_app.news.data.remote;

public class NewsApiClient {

    private static NewsApiClient instance;

    public NewsApiClient() {
    }

    public static NewsApiClient getInstance() {
        if (instance == null) {
            instance = new NewsApiClient();
        }
        return instance;
    }
}
