package com.example.primeraapp.domain.usecases.comments

import com.example.primeraapp.data.RepositoryRemote
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(private val repositoryRemote: RepositoryRemote) {
    operator fun invoke(id: Int) = repositoryRemote.fetchComments(id)
}