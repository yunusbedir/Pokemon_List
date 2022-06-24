package com.yunusbedir.pokem.domain

sealed class ResultData {
    data class Success<DATA>(val data: DATA) : ResultData()
    data class Fail<ERROR>(val message: ERROR) : ResultData()
}