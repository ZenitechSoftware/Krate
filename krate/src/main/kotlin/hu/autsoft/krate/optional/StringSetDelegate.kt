package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class StringSetDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Set<String>? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getStringSet(key, emptySet())
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putStringSet(key, value) }
        }
    }

}
