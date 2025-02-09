package com.example.primeraapp.data.remote.apiServices

import com.example.primeraapp.data.remote.modelo.CommentRemote
import com.example.primeraapp.data.remote.modelo.PostRemote
import com.example.primeraapp.domain.modelo.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @GET("posts/{id}/comments")
    suspend fun getCommentsOfPost(@Path("id") id: Int): Response<List<CommentRemote>>

    @GET("users/{id}/posts")
    suspend fun getPostsOfUser(@Path("id") id: Int): Response<List<PostRemote>>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>

    @POST("posts")
    suspend fun postPost(@Body post: Post): Response<Unit>

}