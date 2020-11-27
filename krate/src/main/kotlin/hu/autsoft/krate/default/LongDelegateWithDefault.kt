package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class LongDelegateWithDefault(
        private val key: String,
        private val default: Long,
) : ReadWriteProperty<Krate, Long> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Long {
        return thisRef.sharedPreferences.getLong(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Long) {
        thisRef.sharedPreferences.edit { putLong(key, value) }
    }

}
