package com.example.houselist_with_di.utility

sealed class DataState <out R, out S> {
    data class Success<out T, out A>(val data: T, val pages: A): DataState<T,A>()
    data class Error(val exception: Exception): DataState<Nothing, Nothing>()
    object Loading: DataState<Nothing, Nothing>()
}