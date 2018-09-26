package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class IntDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Int
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return sharedPreferences.getInt(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        sharedPreferences.edit { putInt(key, value) }
    }

}
