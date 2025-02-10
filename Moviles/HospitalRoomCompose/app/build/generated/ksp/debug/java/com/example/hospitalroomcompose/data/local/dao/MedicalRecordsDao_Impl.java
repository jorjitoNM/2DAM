package com.example.hospitalroomcompose.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import com.example.hospitalroomcompose.data.common.LocalDateConverters;
import com.example.hospitalroomcompose.data.common.MedicationsConverter;
import com.example.hospitalroomcompose.data.local.model.MedicalRecordEntity;
import com.example.hospitalroomcompose.data.local.model.PatientEntity;
import com.example.hospitalroomcompose.data.local.model.PatientWithMedicalRecords;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MedicalRecordsDao_Impl implements MedicalRecordsDao {
  private final RoomDatabase __db;

  private final LocalDateConverters __localDateConverters = new LocalDateConverters();

  private final MedicationsConverter __medicationsConverter = new MedicationsConverter();

  public MedicalRecordsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object getPatientMedicalRecords(final int id,
      final Continuation<? super List<PatientWithMedicalRecords>> $completion) {
    final String _sql = "SELECT * FROM patients WHERE patientId == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PatientWithMedicalRecords>>() {
      @Override
      @NonNull
      public List<PatientWithMedicalRecords> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "paid");
          final LongSparseArray<ArrayList<MedicalRecordEntity>> _collectionMedicalRecords = new LongSparseArray<ArrayList<MedicalRecordEntity>>();
          while (_cursor.moveToNext()) {
            final long _tmpKey;
            _tmpKey = _cursor.getLong(_cursorIndexOfPatientId);
            if (!_collectionMedicalRecords.containsKey(_tmpKey)) {
              _collectionMedicalRecords.put(_tmpKey, new ArrayList<MedicalRecordEntity>());
            }
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipmedicalRecordsAscomExampleHospitalroomcomposeDataLocalModelMedicalRecordEntity(_collectionMedicalRecords);
          final List<PatientWithMedicalRecords> _result = new ArrayList<PatientWithMedicalRecords>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PatientWithMedicalRecords _item;
            final PatientEntity _tmpPatient;
            final int _tmpPatientId;
            _tmpPatientId = _cursor.getInt(_cursorIndexOfPatientId);
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
            _tmpPatient = new PatientEntity(_tmpPatientId,_tmpName,_tmpBirthDate,_tmpPhone,_tmpPaid);
            final ArrayList<MedicalRecordEntity> _tmpMedicalRecordsCollection;
            final long _tmpKey_1;
            _tmpKey_1 = _cursor.getLong(_cursorIndexOfPatientId);
            _tmpMedicalRecordsCollection = _collectionMedicalRecords.get(_tmpKey_1);
            _item = new PatientWithMedicalRecords(_tmpPatient,_tmpMedicalRecordsCollection);
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

  @Override
  public Object getMedicalRecord(final int id,
      final Continuation<? super MedicalRecordEntity> $completion) {
    final String _sql = "SELECT * FROM medicalRecords WHERE recordId == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MedicalRecordEntity>() {
      @Override
      @NonNull
      public MedicalRecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "doctorId");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfMedications = CursorUtil.getColumnIndexOrThrow(_cursor, "medications");
          final MedicalRecordEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpRecordId;
            _tmpRecordId = _cursor.getInt(_cursorIndexOfRecordId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final LocalDate _tmpDate;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfDate);
            _tmpDate = __localDateConverters.stringToLocalDate(_tmp);
            final int _tmpDoctorId;
            _tmpDoctorId = _cursor.getInt(_cursorIndexOfDoctorId);
            final int _tmpPatientId;
            _tmpPatientId = _cursor.getInt(_cursorIndexOfPatientId);
            final List<String> _tmpMedications;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfMedications);
            _tmpMedications = __medicationsConverter.toList(_tmp_1);
            _result = new MedicalRecordEntity(_tmpRecordId,_tmpDescription,_tmpDate,_tmpDoctorId,_tmpPatientId,_tmpMedications);
          } else {
            _result = null;
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

  private void __fetchRelationshipmedicalRecordsAscomExampleHospitalroomcomposeDataLocalModelMedicalRecordEntity(
      @NonNull final LongSparseArray<ArrayList<MedicalRecordEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipmedicalRecordsAscomExampleHospitalroomcomposeDataLocalModelMedicalRecordEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `medicalRecords`.`recordId` AS `recordId`,`medicalRecords`.`description` AS `description`,`medicalRecords`.`date` AS `date`,`medicalRecords`.`doctorId` AS `doctorId`,`medicalRecords`.`patientId` AS `patientId`,`medicalRecords`.`medications` AS `medications`,_junction.`patientId` FROM `PatientMedicalRecordsCrossRef` AS _junction INNER JOIN `medicalRecords` ON (_junction.`recordId` = `medicalRecords`.`recordId`) WHERE _junction.`patientId` IN (");
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
      // _junction.patientId;
      final int _itemKeyIndex = 6;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfRecordId = 0;
      final int _cursorIndexOfDescription = 1;
      final int _cursorIndexOfDate = 2;
      final int _cursorIndexOfDoctorId = 3;
      final int _cursorIndexOfPatientId = 4;
      final int _cursorIndexOfMedications = 5;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<MedicalRecordEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final MedicalRecordEntity _item_1;
          final int _tmpRecordId;
          _tmpRecordId = _cursor.getInt(_cursorIndexOfRecordId);
          final String _tmpDescription;
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
          final LocalDate _tmpDate;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfDate);
          _tmpDate = __localDateConverters.stringToLocalDate(_tmp);
          final int _tmpDoctorId;
          _tmpDoctorId = _cursor.getInt(_cursorIndexOfDoctorId);
          final int _tmpPatientId;
          _tmpPatientId = _cursor.getInt(_cursorIndexOfPatientId);
          final List<String> _tmpMedications;
          final String _tmp_1;
          _tmp_1 = _cursor.getString(_cursorIndexOfMedications);
          _tmpMedications = __medicationsConverter.toList(_tmp_1);
          _item_1 = new MedicalRecordEntity(_tmpRecordId,_tmpDescription,_tmpDate,_tmpDoctorId,_tmpPatientId,_tmpMedications);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
