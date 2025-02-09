package com.example.primeraapp.domain.usecases.posts

import com.example.primeraapp.data.RepositoryRemote
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repositorio: RepositoryRemote) {
    operator fun invoke(id: Int) = repositorio.fetchPosts(id)
}