package hu.autsoft.krate.optional

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class StringDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getString(key, null)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putString(key, value) }
        }
    }

}
