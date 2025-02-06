package com.example.hospitalroomcompose.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.hospitalroomcompose.data.common.LocalDateConverters;
import com.example.hospitalroomcompose.data.local.model.PatientEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PatientsDao_Impl implements PatientsDao {
  private final RoomDatabase __db;

  private final LocalDateConverters __localDateConverters = new LocalDateConverters();

  public PatientsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object getAllPatients(final Continuation<? super List<PatientEntity>> $completion) {
    final String _sql = "SELECT * FROM patients";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PatientEntity>>() {
      @Override
      @NonNull
      public List<PatientEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "paid");
          final List<PatientEntity> _result = new ArrayList<PatientEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PatientEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final LocalDate _tmpBirthDate;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfBirthDate);
            _tmpBirthDate = __localDateConverters.stringToLocalDate(_tmp);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final int _tmpPaid;
            _tmpPaid = _cursor.getInt(_cursorIndexOfPaid);
            _item = new PatientEntity(_tmpId,_tmpName,_tmpBirthDate,_tmpPhone,_tmpPaid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
