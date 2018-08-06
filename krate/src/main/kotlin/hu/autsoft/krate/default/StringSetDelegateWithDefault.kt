package hu.autsoft.krate.default

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class StringSetDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Set<String>
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> {
        return sharedPreferences.getStringSet(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
        sharedPreferences.edit { putStringSet(key, value) }
    }

}
