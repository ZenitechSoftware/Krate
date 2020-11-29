package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class IntDelegate(
        private val key: String,
) : ReadWriteProperty<Krate, Int?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Int? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getInt(key, 0)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Int?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putInt(key, value) }
        }
    }

}
