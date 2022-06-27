package com.yunusbedir.pokem.domain

sealed class ResultData<T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Fail<T>(val message: Error) : ResultData<T>()
}