package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class IntDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getInt(key, 0)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putInt(key, value) }
        }
    }

}
