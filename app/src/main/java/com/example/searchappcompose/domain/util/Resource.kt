package com.example.searchappcompose.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String?) : Resource<T>(data, message)
    class Loading<T>(message: String? = null) : Resource<T>(null, message)
}