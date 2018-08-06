package hu.autsoft.krate.default

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class FloatDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Float
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Float {
        return sharedPreferences.getFloat(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        sharedPreferences.edit { putFloat(key, value) }
    }

}
