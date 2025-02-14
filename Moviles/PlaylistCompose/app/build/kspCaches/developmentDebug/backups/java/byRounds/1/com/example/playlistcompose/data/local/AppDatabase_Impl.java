package com.example.playlistcompose.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.playlistcompose.data.local.dao.PlaylistDao;
import com.example.playlistcompose.data.local.dao.PlaylistDao_Impl;
import com.example.playlistcompose.data.local.dao.UserDao;
import com.example.playlistcompose.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PlaylistDao _playlistDao;

  private volatile UserDao _userDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `Song` (`songId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `songName` TEXT NOT NULL, `artist` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Playlist` (`playlistId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlistName` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `PlaylistSongCrossRef` (`playlistId` INTEGER NOT NULL, `songId` INTEGER NOT NULL, PRIMARY KEY(`playlistId`, `songId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'da11ed74a86a984501f2dbd2df0e1211')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `Song`");
        db.execSQL("DROP TABLE IF EXISTS `Playlist`");
        db.execSQL("DROP TABLE IF EXISTS `PlaylistSongCrossRef`");
        db.execSQL("DROP TABLE IF EXISTS `User`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSong = new HashMap<String, TableInfo.Column>(3);
        _columnsSong.put("songId", new TableInfo.Column("songId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSong.put("songName", new TableInfo.Column("songName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSong.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSong = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSong = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSong = new TableInfo("Song", _columnsSong, _foreignKeysSong, _indicesSong);
        final TableInfo _existingSong = TableInfo.read(db, "Song");
        if (!_infoSong.equals(_existingSong)) {
          return new RoomOpenHelper.ValidationResult(false, "Song(com.example.playlistcompose.domain.model.Song).\n"
                  + " Expected:\n" + _infoSong + "\n"
                  + " Found:\n" + _existingSong);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylist = new HashMap<String, TableInfo.Column>(2);
        _columnsPlaylist.put("playlistId", new TableInfo.Column("playlistId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylist.put("playlistName", new TableInfo.Column("playlistName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylist = new TableInfo("Playlist", _columnsPlaylist, _foreignKeysPlaylist, _indicesPlaylist);
        final TableInfo _existingPlaylist = TableInfo.read(db, "Playlist");
        if (!_infoPlaylist.equals(_existingPlaylist)) {
          return new RoomOpenHelper.ValidationResult(false, "Playlist(com.example.playlistcompose.domain.model.Playlist).\n"
                  + " Expected:\n" + _infoPlaylist + "\n"
                  + " Found:\n" + _existingPlaylist);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylistSongCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsPlaylistSongCrossRef.put("playlistId", new TableInfo.Column("playlistId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSongCrossRef.put("songId", new TableInfo.Column("songId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylistSongCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylistSongCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylistSongCrossRef = new TableInfo("PlaylistSongCrossRef", _columnsPlaylistSongCrossRef, _foreignKeysPlaylistSongCrossRef, _indicesPlaylistSongCrossRef);
        final TableInfo _existingPlaylistSongCrossRef = TableInfo.read(db, "PlaylistSongCrossRef");
        if (!_infoPlaylistSongCrossRef.equals(_existingPlaylistSongCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "PlaylistSongCrossRef(com.example.playlistcompose.data.local.model.PlaylistSongCrossRef).\n"
                  + " Expected:\n" + _infoPlaylistSongCrossRef + "\n"
                  + " Found:\n" + _existingPlaylistSongCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(3);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(db, "User");
        if (!_infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.playlistcompose.domain.model.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "da11ed74a86a984501f2dbd2df0e1211", "0f4a104fad8a5d42e7eee5a8377d5dbb");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Song","Playlist","PlaylistSongCrossRef","User");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Song`");
      _db.execSQL("DELETE FROM `Playlist`");
      _db.execSQL("DELETE FROM `PlaylistSongCrossRef`");
      _db.execSQL("DELETE FROM `User`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlaylistDao.class, PlaylistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PlaylistDao playlistDao() {
    if (_playlistDao != null) {
      return _playlistDao;
    } else {
      synchronized(this) {
        if(_playlistDao == null) {
          _playlistDao = new PlaylistDao_Impl(this);
        }
        return _playlistDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
