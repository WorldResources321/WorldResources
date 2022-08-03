package com.example.world_resources_app.news.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.world_resources_app.news.models.NewsItem;


@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NewsRoomDatabase extends RoomDatabase {

    public static NewsRoomDatabase INSTANCE;
    
    public abstract ArticleDao articleDao();

    public static NewsRoomDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsRoomDatabase.class, "news_db")
//                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
