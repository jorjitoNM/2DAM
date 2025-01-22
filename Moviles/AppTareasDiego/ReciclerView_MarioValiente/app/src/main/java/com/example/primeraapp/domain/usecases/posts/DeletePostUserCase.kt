package com.example.primeraapp.domain.usecases.posts

import com.example.primeraapp.data.RepositoryRemote
import javax.inject.Inject

class DeletePostUserCase @Inject constructor(private val repositorio: RepositoryRemote) {
    operator fun invoke(postId: Int) = repositorio.deletePost(postId)
}