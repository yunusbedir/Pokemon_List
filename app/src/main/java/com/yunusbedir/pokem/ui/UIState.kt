package com.yunusbedir.pokem.ui

sealed interface UIState<Data> {
    class Loading<Nothing> : UIState<Nothing>
    data class ErrorMessage<Nothing>(val errorMessage: String) : UIState<Nothing>
    data class Success<DATA>(val data: DATA) : UIState<DATA>
}