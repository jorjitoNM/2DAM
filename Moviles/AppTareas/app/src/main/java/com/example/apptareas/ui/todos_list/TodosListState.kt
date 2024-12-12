package com.example.apptareas.ui.todos_list

import com.example.apptareas.domain.model.Todo
import com.example.apptareas.ui.common.UiEvent

data class TodosListState (
    val todos : List<Todo> = emptyList(),
    val filteredTodos : List<Todo> = emptyList(),
    val filtered : Boolean = false,
    val appEvent : UiEvent? = null,
)