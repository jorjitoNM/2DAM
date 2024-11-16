package com.example.myapplication.data.remote

import retrofit2.Response


sealed class NetworkResult<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()


    fun <R> map(transform: (data: T?) -> Unit) : NetworkResult<R> =
        when(this){
            is Error -> TODO()
            is Loading -> Loading()
            is Success -> TODO()
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