package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class LongDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, Long?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Long? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getLong(key, 0L)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Long?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putLong(key, value) }
        }
    }

}
