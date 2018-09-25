package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class FloatDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Float? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getFloat(key, 0f)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putFloat(key, value) }
        }
    }

}
