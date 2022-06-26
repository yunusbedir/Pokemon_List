package com.yunusbedir.pokem.domain.model.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailResult(
    val pokemonName: String?,
    val height: String?,
    val weight: String?,
    val iconFront: String?,
    val iconBack: String?
) : Parcelable