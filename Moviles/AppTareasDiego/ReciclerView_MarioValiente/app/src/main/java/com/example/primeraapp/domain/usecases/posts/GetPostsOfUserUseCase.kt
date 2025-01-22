package com.example.primeraapp.domain.usecases.posts

import com.example.primeraapp.data.RepositoryRemote
import javax.inject.Inject

class GetPostsOfUserUseCase @Inject constructor(private val repositorio: RepositoryRemote) {
    operator fun invoke(userId: Int) = repositorio.fetchPostsOfUser(userId)
}