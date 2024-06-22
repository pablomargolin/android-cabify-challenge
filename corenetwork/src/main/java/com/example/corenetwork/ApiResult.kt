package com.example.corenetwork

sealed class ApiResult<T> {
    class SUCCESS<T>(val result: T): ApiResult<T>()
    class ERROR<T>(val error: String): ApiResult<T>()
}