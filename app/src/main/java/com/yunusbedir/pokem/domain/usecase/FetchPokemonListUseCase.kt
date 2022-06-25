package com.yunusbedir.pokem.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yunusbedir.pokem.data.paging.datasource.PokemonListPagingSource
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    fun invoke(): Flow<PagingData<FetchPokemonListResult.ItemResult>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { PokemonListPagingSource(pokemonRepository) }
    ).flow
}