package com.example.apptareas.domain.usecases.todo_usercases

import com.example.apptareas.data.TodosRepository
import javax.inject.Inject

class GetUserTodosUseCase @Inject constructor(private val todosRepository: TodosRepository) {
    operator fun invoke () = todosRepository.getUserTodos()
}