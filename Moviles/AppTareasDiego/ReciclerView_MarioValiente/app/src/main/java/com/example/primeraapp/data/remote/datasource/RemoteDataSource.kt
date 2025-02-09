package com.example.primeraapp.data.remote.datasource

import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.data.remote.apiServices.PostService
import com.example.primeraapp.data.remote.apiServices.UserService
import com.example.primeraapp.data.remote.modelo.toComment
import com.example.primeraapp.data.remote.modelo.toPost
import com.example.primeraapp.data.remote.modelo.toUser
import com.example.primeraapp.domain.modelo.Comment
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.domain.modelo.User
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val postService: PostService,
    private val userService: UserService,
) : BaseApiResponse() {
    suspend fun fechPosts(id: Int): NetworkResult<List<Post>> =
        safeApiCall { postService.getPostsOfUser(id) }.map { list ->
            list.map { it.toPost() }
        }

    suspend fun fechCommentsOfPost(postId: Int): NetworkResult<List<Comment>> =
        safeApiCall { postService.getCommentsOfPost(postId) }.map { lista ->
            lista.map { it.toComment() }
        }

    suspend fun addPost(post: Post): NetworkResult<Boolean> =
        safeApiCallNoBody { postService.postPost(post) }


    suspend fun fechPostsOfUser(userId: Int): NetworkResult<List<Post>> =
        safeApiCall { postService.getPostsOfUser(userId) }.map { list ->
            list.map { it.toPost() }

        }

    suspend fun fechUser(userId: Int): NetworkResult<User> =
        safeApiCall { userService.getUser(userId) }.map { usuario ->
            usuario.toUser()
        }

    suspend fun updateUser(id: Int, user: User): NetworkResult<Boolean> =
        safeApiCallNoBody { userService.putUser(id, user) }

    suspend fun deletePost(postId: Int): NetworkResult<Boolean> =
        safeApiCallNoBody { postService.deletePost(postId) }
}