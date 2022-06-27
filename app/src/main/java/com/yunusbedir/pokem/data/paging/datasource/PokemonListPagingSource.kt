package com.yunusbedir.pokem.data.paging.datasource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import com.yunusbedir.pokem.domain.toResultModel
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val backend: PokemonRepository,
) : PagingSource<Int, FetchPokemonListResult.ItemResult>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, FetchPokemonListResult.ItemResult> {
        return try {
            val nextPageNumber = params.key ?: 0
            when (val response = backend.fetchPokemonList(nextPageNumber)) {
                is ResultData.Fail -> {
                    LoadResult.Error(Throwable(response.message.errorMessage))
                }
                is ResultData.Success -> {
                    LoadResult.Page(
                        data = response.data.results!!,
                        prevKey = response.data.previous?.toInt(), // Only paging forward.
                        nextKey = response.data.next?.toInt()
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(Throwable(e))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FetchPokemonListResult.ItemResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
