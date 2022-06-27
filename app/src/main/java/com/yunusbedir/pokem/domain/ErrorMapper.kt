package com.yunusbedir.pokem.domain

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorMapper @Inject constructor() {
    fun mapError(throwable: Throwable?): Error {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    500 -> {
                        Error.NetworkError("Internal Server Error")
                    }
                    404 -> {
                        Error.NetworkError("Can not found web service")

                    }
                    else -> {
                        Error.NetworkError("No internet connection")
                    }
                }
            }
            is IOException -> {
                Error.CommonError("Illegal state")
            }
            else -> {
                Error.CommonError("No internet connection")
            }
        }
    }
}
