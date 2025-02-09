package com.example.apptareas.ui.todos_list

interface TodosListEvents {
    data object GetTodos : TodosListEvents
    data object EventDone : TodosListEvents
    data class FilterTodos (val todoName : String) : TodosListEvents
}