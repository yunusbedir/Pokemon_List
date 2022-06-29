package com.yunusbedir.pokem.data.network

import com.yunusbedir.pokem.data.network.model.response.ItemResult
import com.yunusbedir.pokem.data.network.model.response.PokemonDetailResponse
import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import com.yunusbedir.pokem.data.network.model.response.Sprites

object FakeData {

    val pokemonListResponseList = mutableListOf(
        PokemonListResponse(
            count = 1154,
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            previous = null,
            results = listOf(
                ItemResult(
                    name = "bulbasaur",
                    url = "https://pokeapi.co/api/v2/pokemon/1/"
                )
            ),
        ),
        PokemonListResponse(
            count = 1154,
            next = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
            previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
            results = listOf(
                ItemResult(
                    name = "ivysaur",
                    url = "https://pokeapi.co/api/v2/pokemon/2/"
                )
            ),
        )
    )


    val pokemonDetailResponseList = mutableListOf(
        PokemonDetailResponse(
            height = 3,
            id = 1,
            name = "bulbasaur",
            sprites = Sprites(
                backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
                frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
            ),
            weight = 23,
        ),
        PokemonDetailResponse(
            height = 32,
            id = 2,
            name = "ivysaur",
            sprites = Sprites(
                backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/2.png",
                frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png",
            ),
            weight = 23,
        )
    )

}