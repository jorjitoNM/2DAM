package com.example.apptareas.ui.todos_list

interface TodosListEvents {
    data class GetTodos (val userId : Int) : TodosListEvents
    data object EventDone : TodosListEvents
}