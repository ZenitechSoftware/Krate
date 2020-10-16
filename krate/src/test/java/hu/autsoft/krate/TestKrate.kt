package hu.autsoft.krate

import android.content.Context

internal class TestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    var optionalBoolean by booleanPref("optionalBoolean")
    var optionalFloat by floatPref("optionalFloat")
    var optionalInt by intPref("optionalInt")
    var optionalLong by longPref("optionalLong")
    var optionalString by stringPref("optionalString")
    var optionalStringSet by stringSetPref("optionalStringSet")
    var optionalValidatedString by stringPref("validatedString") {
        it?.length ?: 5 == 5
    }
    var defaultValidatedFloat by floatPref("defaultFloat", 0.0f) {
        it in 0.0f..1.0f
    }

}
