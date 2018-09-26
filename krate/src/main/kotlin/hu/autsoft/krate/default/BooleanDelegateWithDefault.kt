package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class BooleanDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Boolean
) : ReadWriteProperty<Krate, Boolean> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit { putBoolean(key, value) }
    }

}
