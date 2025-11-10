package com.route.data.base

import com.route.data.dto.ErrorDto
import com.route.domain.base.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import retrofit2.Response

open class BaseRemoteDataSource {

    fun <T> safeAPICall(call: suspend () -> Response<T?>): Flow<Resource<T?>> = flow {
        emit(Resource.Loading())
        try {
            val response = call()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    val errorDto = Json.decodeFromString<ErrorDto>(errorBody ?: "")
                    errorDto.message ?: errorDto.statusMsg ?: "Unknown error"
                } catch (e: Exception) {
                    "Failed to parse error response: $e"
                }
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong."))
        }
    }
}
