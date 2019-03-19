package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class BooleanDelegateWithDefault(
        private val key: String,
        private val default: Boolean
) : ReadWriteProperty<Krate, Boolean> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean {
        return thisRef.sharedPreferences.getBoolean(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean) {
        thisRef.sharedPreferences.edit { putBoolean(key, value) }
    }

}
