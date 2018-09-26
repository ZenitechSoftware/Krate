package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class LongDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Long
) : ReadWriteProperty<Krate, Long> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Long {
        return sharedPreferences.getLong(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Long) {
        sharedPreferences.edit { putLong(key, value) }
    }

}
