package hu.autsoft.krate.default

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class LongDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Long
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return sharedPreferences.getLong(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        sharedPreferences.edit { putLong(key, value) }
    }

}
