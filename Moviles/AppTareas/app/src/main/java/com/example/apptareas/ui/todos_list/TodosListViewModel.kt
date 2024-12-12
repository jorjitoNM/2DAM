package com.example.apptareas.ui.todos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.usecases.todo_usercases.FilterTodosUseCase
import com.example.apptareas.domain.usecases.todo_usercases.GetUserTodosUseCase
import com.example.apptareas.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosListViewModel @Inject constructor (
    private val getUserTodos : GetUserTodosUseCase,
    private val filterTodosUseCase: FilterTodosUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodosListState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent (event : TodosListEvents) {
        when (event) {
            is TodosListEvents.GetTodos -> getTodos()
            is TodosListEvents.EventDone -> _uiState.update{ it.copy(appEvent = null) }
            is TodosListEvents.FilterTodos -> filterTodos(event.todoName)
        }
    }

    private fun filterTodos(todoName: String) {
        val todos = filterTodosUseCase(todoName,_uiState.value.todos)
        _uiState.update { it.copy(filteredTodos = todos, filtered = true) }
    }

    private fun getTodos() {
        viewModelScope.launch {
            when (val todos = getUserTodos.invoke()) {
                is NetworkResult.Success -> _uiState.update{ it.copy(todos = todos.data, filtered = false) }
                is NetworkResult.Error -> _uiState.update { it.copy(filtered = false, appEvent = UiEvent.ShowSnackbar(todos.message)) }
                is NetworkResult.Loading -> TODO()
            }
        }
    }
}