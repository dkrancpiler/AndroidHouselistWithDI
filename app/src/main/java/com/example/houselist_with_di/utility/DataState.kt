package com.example.houselist_with_di.utility

sealed class DataState <out List> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}