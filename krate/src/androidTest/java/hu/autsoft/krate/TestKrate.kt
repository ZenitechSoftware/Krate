package hu.autsoft.krate

import android.content.Context

class TestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    var optionalBoolean by booleanPref("optionalBoolean")
    var optionalFloat by floatPref("optionalFloat")
    var optionalInt by intPref("optionalInt")
    var optionalLong by longPref("optionalLong")
    var optionalString by stringPref("optionalString")
    var optionalStringSet by stringSetPref("optionalStringSet")

}
