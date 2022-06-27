package com.yunusbedir.pokem.domain.util

import com.yunusbedir.pokem.domain.ErrorMapper
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import com.yunusbedir.pokem.domain.Error
import com.yunusbedir.pokem.domain.ResultData


suspend fun <DATA, DOMAIN> suspendCall(
    coroutineContext: CoroutineContext,
    errorMapper: ErrorMapper,
    call: suspend () -> DATA?,
    map: (data: DATA) -> DOMAIN
): ResultData<DOMAIN> = withContext(coroutineContext) {
    return@withContext try {
        val result = call()
        if (result == null) {
            ResultData.Fail(Error.NullResponseError)
        } else {
            ResultData.Success(map(result))
        }
    } catch (expected: Exception) {
        ResultData.Fail(errorMapper.mapError(expected.cause))
    }
}