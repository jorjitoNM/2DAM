package com.example.primeraapp.domain.usecases.posts

import com.example.primeraapp.data.RepositoryRemote
import com.example.primeraapp.domain.modelo.Post
import javax.inject.Inject

class AddPostUseCase @Inject constructor(private val repositoryRemote: RepositoryRemote) {
    operator fun invoke(post: Post) = repositoryRemote.addPost(post)
}
