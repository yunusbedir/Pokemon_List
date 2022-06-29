package com.yunusbedir.pokem.domain.usecase

import app.cash.turbine.test
import com.yunusbedir.pokem.data.network.repository.FakePokemonRepository
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.ui.UIState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPokemonDetailUseCaseTest {

    private val pokemonDetailList = mutableListOf<PokemonDetailResult>(
        PokemonDetailResult(
            pokemonName = "test",
            height = "23",
            weight = "32",
            iconFront = "https://www.test.com/iconFront.jpg",
            iconBack = "https://www.test.com/iconBack.jpg"
        ),
        PokemonDetailResult(
            pokemonName = "test2",
            height = "232",
            weight = "322",
            iconFront = "https://www.test.com/iconFront.jpg",
            iconBack = "https://www.test.com/iconBack.jpg"
        )
    )

    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase
    private lateinit var fakePokemonRepository: FakePokemonRepository

    @Before
    fun setUp() {
        fakePokemonRepository = FakePokemonRepository()
        fakePokemonRepository.insertPokemonDetail(pokemonDetailList)
        getPokemonDetailUseCase = GetPokemonDetailUseCase(fakePokemonRepository)
    }

    @Test
    fun `getPokemonDetailUseCase is Success`() = runBlocking {
        val id = 1
        val getPokemon = getPokemonDetailUseCase.invoke(id)
        getPokemon.test {
            val result = awaitItem()
            assert(result is UIState.Success)
            assertEquals((result as UIState.Success).data, pokemonDetailList[id])
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getPokemonDetailUseCase is ErrorMessage`() = runBlocking {
        val id = 4
        getPokemonDetailUseCase.invoke(id).test {
            val result = awaitItem()
            assert(result is UIState.ErrorMessage)
            cancelAndConsumeRemainingEvents()
        }
    }
}