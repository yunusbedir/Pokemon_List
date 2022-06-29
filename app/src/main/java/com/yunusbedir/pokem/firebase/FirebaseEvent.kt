package com.yunusbedir.pokem.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

enum class FirebaseEvent(val eventName: String, var params: Bundle = Bundle()) {
    SHOW_POKEMON_IN_OVERLAY("show_pokemon_in_overlay")
}

/** Event Send to firebase analytics **/
fun FirebaseAnalytics.trackEventFirebaseAnalytics(firebaseEvent: FirebaseEvent) {
    logEvent(firebaseEvent.eventName, firebaseEvent.params)
}