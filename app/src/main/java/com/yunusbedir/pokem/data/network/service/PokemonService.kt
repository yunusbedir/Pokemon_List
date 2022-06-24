package com.yunusbedir.pokem.data.network.service

import com.yunusbedir.pokem.data.network.model.response.PokemonDetailResponse
import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon?limit=20")
    suspend fun fetchPokemonList(@Query("offset") offset: Int): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse
}