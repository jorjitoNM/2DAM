package com.example.apptareas.domain.usecases

import com.example.apptareas.data.remote.TodosRepository
import javax.inject.Inject

class GetUserTodosUseCase @Inject constructor(private val todosRepository: TodosRepository) {
    suspend operator fun invoke (userId : Int) {
        todosRepository.getUserTodos(userId)
    }
}