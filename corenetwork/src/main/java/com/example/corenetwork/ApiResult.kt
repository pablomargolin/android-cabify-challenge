package com.example.corenetwork

sealed class ApiResult<T> {
    class Success<T>(val result: T): ApiResult<T>()
    class Error<T>(val error: String): ApiResult<T>()
}