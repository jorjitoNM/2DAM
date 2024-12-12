package com.example.apptareas.domain.usecases.todo_usercases

import com.example.apptareas.domain.model.Todo
import javax.inject.Inject

class FilterTodosUseCase @Inject constructor() {
    operator fun invoke (todoName : String, todos : List<Todo>) : List<Todo> =
        todos.filter { t -> t.title.contains(todoName) }
}