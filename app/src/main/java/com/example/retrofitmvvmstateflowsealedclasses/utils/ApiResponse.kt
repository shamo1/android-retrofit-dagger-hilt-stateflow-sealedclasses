package com.example.retrofitmvvmstateflowsealedclasses.utils

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T?) : ApiResponse<T>()
    data class Failure<T>(val message: String? = null, val data: T? = null)  :ApiResponse<T>()
    object  Loading  :ApiResponse<Nothing>()
    object  INITIALSTATE : ApiResponse<Nothing>()
}