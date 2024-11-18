package com.example.myapplication.data.remote

import retrofit2.Response


sealed class NetworkResult<T>{

    class Success<T>(val data: T) : NetworkResult<T>()

    class Error<T>(val message: String) : NetworkResult<T>()

    class Loading<T> : NetworkResult<T>()

    fun <R> map( transform :(data: T) -> R) : NetworkResult<R> =
        when(this){
            is Error -> Error(message)
            is Loading -> Loading()
            is Success -> Success(transform(data))
        }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return NetworkResult.Success(body)
            }
        }
        return NetworkResult.Error("${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return NetworkResult.Error(e.message ?: e.toString())
    }
}