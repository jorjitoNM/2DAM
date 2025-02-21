package com.example.examen2evajorgenovillo.ui.informes_list

import androidx.lifecycle.ViewModel
import com.example.examen2evajorgenovillo.R
import com.example.examen2evajorgenovillo.common.StringProvider
import com.example.examen2evajorgenovillo.domain.usecases.informes.GetAllInformesUseCase
import com.example.examen2evajorgenovillo.ui.alumnos_list.AlumnosListEvents
import com.example.examen2evajorgenovillo.ui.alumnos_list.AlumnosListState
import com.example.examen2evajorgenovillo.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InformesListViewModel @Inject constructor(
    private val getAllInformesUseCase : GetAllInformesUseCase,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<InformesListState> = MutableStateFlow(InformesListState())
    val uiState: StateFlow<InformesListState> = _uiState.asStateFlow()

    fun handleEvent (event : InformesListEvents) {
        when (event) {
            is InformesListEvents.GetAll -> getAll()
            is InformesListEvents.EventDone -> _uiState.update {
                it.copy(
                    event = null
                )
            }
        }
    }

    private fun getAll () {
        try {
            _uiState.update {
                it.copy(
                   informes = getAllInformesUseCase.invoke(),
                )
            }
        } catch (e : Exception) {
            Timber.e(e.message,e)
            _uiState.update {
                it.copy(
                    event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.error_getting_informes) + e.message),
                )
            }
        }
    }
}