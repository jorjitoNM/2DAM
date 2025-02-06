package com.example.apptareascompose.ui.medical_record_details;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aR!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001b"}, d2 = {"Lcom/example/apptareascompose/ui/medical_record_details/MedicalRecordDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "getMedicalRecordUseCase", "Lcom/example/apptareascompose/domain/usecases/medical_records/GetMedicalRecordUseCase;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/example/apptareascompose/domain/usecases/medical_records/GetMedicalRecordUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/apptareascompose/ui/medical_record_details/MedicalRecordDetailsState;", "get_uiState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "_uiState$delegate", "Lkotlin/Lazy;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "getMedicalRecord", "", "recordId", "", "handleEvent", "event", "Lcom/example/apptareascompose/ui/medical_record_details/MedicalRecordDetailsEvents;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MedicalRecordDetailsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.apptareascompose.domain.usecases.medical_records.GetMedicalRecordUseCase getMedicalRecordUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy _uiState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsState> uiState = null;
    
    @javax.inject.Inject()
    public MedicalRecordDetailsViewModel(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.domain.usecases.medical_records.GetMedicalRecordUseCase getMedicalRecordUseCase, @com.example.primeraapp.di.IoDispatcher()
    @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getDispatcher() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsState> get_uiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsEvents event) {
    }
    
    private final void getMedicalRecord(int recordId) {
    }
}