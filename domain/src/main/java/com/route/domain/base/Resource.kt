package com.route.domain.base

sealed interface Resource<T> {
    class Initial<T> : Resource<T>
    class Loading<T> : Resource<T>
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val errorMessage: String) : Resource<T>
}

fun <T, R> Resource<T>.mapResource(mapper: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Error -> Resource.Error(this.errorMessage)

        is Resource.Initial -> Resource.Initial()

        is Resource.Loading -> Resource.Loading()

        is Resource.Success -> Resource.Success(mapper(this.data))
    }
}
