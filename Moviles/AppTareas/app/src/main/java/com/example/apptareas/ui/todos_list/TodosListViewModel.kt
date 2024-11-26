package com.example.apptareas.ui.todos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptareas.domain.usecases.GetUserTodosUseCase
import com.example.apptareas.ui.login.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodosListViewModel @Inject constructor (
    private val getUserTodos : GetUserTodosUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(TodosListState())
    val uiState: LiveData<TodosListState> get() = _uiState
}