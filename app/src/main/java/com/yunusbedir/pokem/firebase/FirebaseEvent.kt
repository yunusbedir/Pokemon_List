package com.yunusbedir.pokem.firebase

import android.os.Bundle

enum class FirebaseEvent(val eventName: String, var params: Bundle = Bundle()) {
    SHOW_POKEMON_IN_OVERLAY("show_pokemon_in_overlay")
}