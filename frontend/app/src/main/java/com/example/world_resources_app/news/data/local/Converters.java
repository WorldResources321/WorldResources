package com.example.world_resources_app.news.data.local;

import androidx.room.TypeConverter;

import com.example.world_resources_app.news.models.Source;


public class Converters {

    @TypeConverter
    public String fromSource(Source source) {
        return source.getName();
    }

    @TypeConverter
    public Source toSource(String name) {
        return new Source(name);
    }
}
