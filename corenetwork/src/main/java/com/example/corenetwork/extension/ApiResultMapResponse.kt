package com.example.corenetwork.extension

import com.example.corenetwork.ApiResult
import retrofit2.Response

inline fun <reified T, U> mapResponse(
    response: Response<T>,
    successResponse: (responseBody: T) -> U
): ApiResult<U>{
    return try {
        if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!
            val result = successResponse.invoke(body)
            ApiResult.Success(result)
        } else {
            ApiResult.Error("Error getting response")
        }
    }
    catch (exception: Exception){
        ApiResult.Error(exception.message ?: "")
    }
}