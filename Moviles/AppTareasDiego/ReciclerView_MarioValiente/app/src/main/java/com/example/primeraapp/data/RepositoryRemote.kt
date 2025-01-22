package com.example.primeraapp.data

import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.data.remote.datasource.RemoteDataSource
import com.example.primeraapp.data.utils.ConstantesData
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.Comment
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.domain.modelo.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryRemote @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

    fun fetchPosts(id: Int): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.fechPosts(id)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_FETCH_POSTS))
        }.flowOn(ioDispatcher)
    }

    fun fetchComments(postId: Int): Flow<NetworkResult<List<Comment>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.fechCommentsOfPost(postId)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_FETCH_COMMENTS))
        }.flowOn(ioDispatcher)
    }

    fun fetchUser(userId: Int): Flow<NetworkResult<User>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.fechUser(userId)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_FETCH_USER))
        }.flowOn(ioDispatcher)
    }

    fun addPost(post: Post): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.addPost(post)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_ADD_POST))
        }.flowOn(ioDispatcher)
    }

    fun fetchPostsOfUser(userId: Int): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.fechPostsOfUser(userId)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_FETCH_POSTS_USER))
        }.flowOn(ioDispatcher)
    }

    fun deletePost(postId: Int): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.deletePost(postId)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_DELETE_POST))
        }.flowOn(ioDispatcher)
    }

    fun updateUser(id: Int, user: User): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.updateUser(id, user)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: ConstantesData.ERROR_UPDATE_USER))
        }.flowOn(ioDispatcher)
    }


}
