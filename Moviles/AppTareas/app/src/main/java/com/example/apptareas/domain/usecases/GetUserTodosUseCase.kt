package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.data.remote.TodosRepository
import com.example.apptareas.domain.model.Todo
import javax.inject.Inject

class GetUserTodosUseCase @Inject constructor(private val todosRepository: TodosRepository) {
    suspend operator fun invoke () : NetworkResult<List<Todo>> {
        return todosRepository.getUserTodos()
    }
}