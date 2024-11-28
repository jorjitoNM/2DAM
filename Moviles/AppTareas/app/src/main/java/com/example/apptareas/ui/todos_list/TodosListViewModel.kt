package com.example.apptareas.ui.todos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.usecases.GetUserTodosUseCase
import com.example.apptareas.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosListViewModel @Inject constructor (
    private val getUserTodos : GetUserTodosUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(TodosListState())
    val uiState: LiveData<TodosListState> get() = _uiState

    fun handleEvent (event : TodosListEvents) {
        when (event) {
            is TodosListEvents.GetTodos -> getTodos()
            is TodosListEvents.EventDone -> _uiState.value = _uiState.value?.copy(appEvent = null)
        }
    }

    private fun getTodos() {
        viewModelScope.launch {
            when (val todos = getUserTodos.invoke()) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(todos = todos.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value?.copy(
                        appEvent =
                        UiEvent.ShowSnackbar(todos.message)
                    )
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }
}