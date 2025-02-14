package com.example.playlistcompose.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import com.example.playlistcompose.data.local.model.PlaylistWithSongs;
import com.example.playlistcompose.domain.model.Playlist;
import com.example.playlistcompose.domain.model.Song;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlaylistDao_Impl implements PlaylistDao {
  private final RoomDatabase __db;

  public PlaylistDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<PlaylistWithSongs> getPlaylistsWithSongs() {
    final String _sql = "SELECT * FROM Playlist";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfPlaylistId = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistId");
        final int _cursorIndexOfPlaylistName = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistName");
        final LongSparseArray<ArrayList<Song>> _collectionSongs = new LongSparseArray<ArrayList<Song>>();
        while (_cursor.moveToNext()) {
          final long _tmpKey;
          _tmpKey = _cursor.getLong(_cursorIndexOfPlaylistId);
          if (!_collectionSongs.containsKey(_tmpKey)) {
            _collectionSongs.put(_tmpKey, new ArrayList<Song>());
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipSongAscomExamplePlaylistcomposeDomainModelSong(_collectionSongs);
        final List<PlaylistWithSongs> _result = new ArrayList<PlaylistWithSongs>(_cursor.getCount());
        while (_cursor.moveToNext()) {
          final PlaylistWithSongs _item;
          final Playlist _tmpPlaylist;
          final long _tmpPlaylistId;
          _tmpPlaylistId = _cursor.getLong(_cursorIndexOfPlaylistId);
          final String _tmpPlaylistName;
          _tmpPlaylistName = _cursor.getString(_cursorIndexOfPlaylistName);
          _tmpPlaylist = new Playlist(_tmpPlaylistId,_tmpPlaylistName);
          final ArrayList<Song> _tmpSongsCollection;
          final long _tmpKey_1;
          _tmpKey_1 = _cursor.getLong(_cursorIndexOfPlaylistId);
          _tmpSongsCollection = _collectionSongs.get(_tmpKey_1);
          _item = new PlaylistWithSongs(_tmpPlaylist,_tmpSongsCollection);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Playlist> getAll() {
    final String _sql = "SELECT * FROM Playlist";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPlaylistId = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistId");
      final int _cursorIndexOfPlaylistName = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistName");
      final List<Playlist> _result = new ArrayList<Playlist>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Playlist _item;
        final long _tmpPlaylistId;
        _tmpPlaylistId = _cursor.getLong(_cursorIndexOfPlaylistId);
        final String _tmpPlaylistName;
        _tmpPlaylistName = _cursor.getString(_cursorIndexOfPlaylistName);
        _item = new Playlist(_tmpPlaylistId,_tmpPlaylistName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipSongAscomExamplePlaylistcomposeDomainModelSong(
      @NonNull final LongSparseArray<ArrayList<Song>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipSongAscomExamplePlaylistcomposeDomainModelSong(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `Song`.`songId` AS `songId`,`Song`.`songName` AS `songName`,`Song`.`artist` AS `artist`,_junction.`playlistId` FROM `PlaylistSongCrossRef` AS _junction INNER JOIN `Song` ON (_junction.`songId` = `Song`.`songId`) WHERE _junction.`playlistId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.playlistId;
      final int _itemKeyIndex = 3;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfSongId = 0;
      final int _cursorIndexOfSongName = 1;
      final int _cursorIndexOfArtist = 2;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<Song> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final Song _item_1;
          final long _tmpSongId;
          _tmpSongId = _cursor.getLong(_cursorIndexOfSongId);
          final String _tmpSongName;
          _tmpSongName = _cursor.getString(_cursorIndexOfSongName);
          final String _tmpArtist;
          _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
          _item_1 = new Song(_tmpSongId,_tmpSongName,_tmpArtist);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
