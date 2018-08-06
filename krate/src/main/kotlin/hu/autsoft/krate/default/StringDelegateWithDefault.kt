package hu.autsoft.krate.default

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class StringDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return sharedPreferences.getString(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

}
