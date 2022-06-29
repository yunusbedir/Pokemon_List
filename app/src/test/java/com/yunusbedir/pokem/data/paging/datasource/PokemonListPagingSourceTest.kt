package com.yunusbedir.pokem.data.paging.datasource

import androidx.paging.PagingSource
import com.yunusbedir.pokem.data.network.FakeData
import com.yunusbedir.pokem.data.network.repository.FakePokemonRepository
import com.yunusbedir.pokem.data.network.repository.PokemonRepositoryImpl
import com.yunusbedir.pokem.data.network.service.FakePokemonService
import com.yunusbedir.pokem.domain.ErrorMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PokemonListPagingSourceTest{

    private lateinit var fakeApi: FakePokemonService
    private lateinit var pokemonRepository: PokemonRepositoryImpl

    @Before
    fun setup(){
        fakeApi = FakePokemonService()
        pokemonRepository = PokemonRepositoryImpl(fakeApi, ErrorMapper())
    }

    @Test
    fun itemKeyedSubredditPagingSource() = runTest {
        val pagingSource = PokemonListPagingSource(pokemonRepository)

        assertEquals<Any>(
            expected = PagingSource.LoadResult.Page(
                data = listOf(FakeData.pokemonListResponseList[0], FakeData.pokemonListResponseList[1]),
                prevKey = FakeData.pokemonListResponseList[0].previous,
                nextKey = FakeData.pokemonListResponseList[1].next
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun pageKeyedSubredditPagingSource() = runTest {
        val pagingSource = PokemonListPagingSource(pokemonRepository)
        assertEquals<Any>(
            expected = PagingSource.LoadResult.Page(
                data = listOf(FakeData.pokemonListResponseList[0], FakeData.pokemonListResponseList[1]),
                prevKey = null,
                nextKey = FakeData.pokemonListResponseList[1].next
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}