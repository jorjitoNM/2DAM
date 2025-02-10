package com.example.hospitalroomcompose.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lcom/example/hospitalroomcompose/data/local/dao/MedicalRecordsDao;", "", "getMedicalRecord", "Lcom/example/hospitalroomcompose/data/local/model/MedicalRecordEntity;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPatientMedicalRecords", "", "Lcom/example/hospitalroomcompose/data/local/model/PatientWithMedicalRecords;", "app_debug"})
@androidx.room.Dao()
public abstract interface MedicalRecordsDao {
    
    @androidx.room.Query(value = "SELECT * FROM patients WHERE patientId == :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPatientMedicalRecords(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.hospitalroomcompose.data.local.model.PatientWithMedicalRecords>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM medicalRecords WHERE recordId == :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMedicalRecord(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.hospitalroomcompose.data.local.model.MedicalRecordEntity> $completion);
}