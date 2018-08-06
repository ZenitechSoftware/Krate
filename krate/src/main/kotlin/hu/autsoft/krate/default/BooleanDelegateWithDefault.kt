package hu.autsoft.krate.default

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class BooleanDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Boolean
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit { putBoolean(key, value) }
    }

}
