package com.example.corenetwork.extension

import com.example.corenetwork.ApiResult
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

inline fun <reified T, U> mapResponse(
    response: Response<ResponseBody>,
    successResponse: (responseBody: T) -> U
): ApiResult<U>{
    return try {
        if (response.isSuccessful && response.body() != null) {
            val gson = Gson()
            val json = response.body()!!.string()

            val products = gson.fromJson(json, T::class.java)
            val result = successResponse.invoke(products)
            ApiResult.SUCCESS(result)
        } else {
            ApiResult.ERROR("Error getting response")
        }
    }
    catch (exception: Exception){
        ApiResult.ERROR(exception.message ?: "")
    }
}