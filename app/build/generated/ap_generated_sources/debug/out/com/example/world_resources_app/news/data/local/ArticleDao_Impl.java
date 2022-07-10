package com.example.world_resources_app.news.data.local;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.world_resources_app.news.models.NewsItem;
import com.example.world_resources_app.news.models.Source;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleDao_Impl implements ArticleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NewsItem> __insertionAdapterOfNewsItem;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<NewsItem> __deletionAdapterOfNewsItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSavedArticles;

  public ArticleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNewsItem = new EntityInsertionAdapter<NewsItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `saved_articles` (`id`,`source`,`author`,`title`,`description`,`url`,`urlToImage`,`publishedAt`,`content`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsItem value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = __converters.fromSource(value.getSource());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUrl());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUrlToImage());
        }
        if (value.getPublishedAt() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getPublishedAt());
        }
        if (value.getContent() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getContent());
        }
      }
    };
    this.__deletionAdapterOfNewsItem = new EntityDeletionOrUpdateAdapter<NewsItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `saved_articles` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllSavedArticles = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM saved_articles";
        return _query;
      }
    };
  }

  @Override
  public void insert(final NewsItem article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNewsItem.insert(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteArticle(final NewsItem article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNewsItem.handle(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllSavedArticles() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSavedArticles.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllSavedArticles.release(_stmt);
    }
  }

  @Override
  public LiveData<List<NewsItem>> getAllSavedArticles() {
    final String _sql = "SELECT * FROM saved_articles ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"saved_articles"}, false, new Callable<List<NewsItem>>() {
      @Override
      public List<NewsItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final List<NewsItem> _result = new ArrayList<NewsItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final NewsItem _item;
            _item = new NewsItem();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final Source _tmpSource;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfSource);
            }
            _tmpSource = __converters.toSource(_tmp);
            _item.setSource(_tmpSource);
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            _item.setAuthor(_tmpAuthor);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            _item.setUrl(_tmpUrl);
            final String _tmpUrlToImage;
            if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
              _tmpUrlToImage = null;
            } else {
              _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
            }
            _item.setUrlToImage(_tmpUrlToImage);
            final String _tmpPublishedAt;
            if (_cursor.isNull(_cursorIndexOfPublishedAt)) {
              _tmpPublishedAt = null;
            } else {
              _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
            }
            _item.setPublishedAt(_tmpPublishedAt);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _item.setContent(_tmpContent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
