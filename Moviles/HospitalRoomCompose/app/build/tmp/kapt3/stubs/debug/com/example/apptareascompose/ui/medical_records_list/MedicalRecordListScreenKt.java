package com.example.apptareascompose.ui.medical_records_list;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a4\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aF\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00052\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\b\u0010\u0014\u001a\u00020\u0001H\u0007\u00a8\u0006\u0015"}, d2 = {"MedicalRecordItem", "", "medicalRecord", "Lcom/example/apptareascompose/domain/model/MedicalRecord;", "onNavigateDetail", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "MedicalRecordListContent", "patientName", "", "medicalRecords", "", "MedicalRecordListScreen", "patientId", "medicalRecordListViewModel", "Lcom/example/apptareascompose/ui/medical_records_list/MedicalRecordListViewModel;", "showSnackbar", "onNavigateDetalle", "PreviewMedicalRecordListScreen", "app_debug"})
public final class MedicalRecordListScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void MedicalRecordListScreen(int patientId, @org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.ui.medical_records_list.MedicalRecordListViewModel medicalRecordListViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> showSnackbar, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onNavigateDetalle) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MedicalRecordListContent(@org.jetbrains.annotations.NotNull()
    java.lang.String patientName, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.apptareascompose.domain.model.MedicalRecord> medicalRecords, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onNavigateDetail) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MedicalRecordItem(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.domain.model.MedicalRecord medicalRecord, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onNavigateDetail, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true, device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
    public static final void PreviewMedicalRecordListScreen() {
    }
}