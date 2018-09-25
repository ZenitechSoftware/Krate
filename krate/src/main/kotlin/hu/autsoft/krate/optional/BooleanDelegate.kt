package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class BooleanDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getBoolean(key, false)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putBoolean(key, value) }
        }
    }

}
