package hu.autsoft.krate.optional

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class LongDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getLong(key, 0L)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putLong(key, value) }
        }
    }

}
