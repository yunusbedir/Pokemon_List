package com.yunusbedir.pokem.di

import com.yunusbedir.pokem.data.network.repository.PokemonRepositoryImpl
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePokemonRepository(pokemonRepository: PokemonRepositoryImpl): PokemonRepository
}