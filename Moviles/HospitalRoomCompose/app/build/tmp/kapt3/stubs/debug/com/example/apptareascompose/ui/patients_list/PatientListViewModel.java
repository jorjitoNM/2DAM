package com.example.apptareascompose.ui.patients_list;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006 "}, d2 = {"Lcom/example/apptareascompose/ui/patients_list/PatientListViewModel;", "Landroidx/lifecycle/ViewModel;", "getAllPatientsUseCase", "Lcom/example/apptareascompose/domain/usecases/patient/GetAllPatientsUseCase;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/example/apptareascompose/domain/usecases/patient/GetAllPatientsUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_uiError", "Lkotlinx/coroutines/channels/Channel;", "Lcom/example/primeraapp/ui/common/UiEvent;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/apptareascompose/ui/patients_list/PatientListState;", "get_uiState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "_uiState$delegate", "Lkotlin/Lazy;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "uiError", "Lkotlinx/coroutines/flow/Flow;", "getUiError", "()Lkotlinx/coroutines/flow/Flow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "getAllPatients", "", "handleEvent", "event", "Lcom/example/apptareascompose/ui/patients_list/PatientListEvents;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PatientListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.apptareascompose.domain.usecases.patient.GetAllPatientsUseCase getAllPatientsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy _uiState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.apptareascompose.ui.patients_list.PatientListState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.example.primeraapp.ui.common.UiEvent> _uiError = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.example.primeraapp.ui.common.UiEvent> uiError = null;
    
    @javax.inject.Inject()
    public PatientListViewModel(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.domain.usecases.patient.GetAllPatientsUseCase getAllPatientsUseCase, @com.example.primeraapp.di.IoDispatcher()
    @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getDispatcher() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.apptareascompose.ui.patients_list.PatientListState> get_uiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.apptareascompose.ui.patients_list.PatientListState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.primeraapp.ui.common.UiEvent> getUiError() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.ui.patients_list.PatientListEvents event) {
    }
    
    private final void getAllPatients() {
    }
}