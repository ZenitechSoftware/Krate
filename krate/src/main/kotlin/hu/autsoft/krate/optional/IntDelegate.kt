package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class IntDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, Int?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Int? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getInt(key, 0)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Int?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putInt(key, value) }
        }
    }

}
