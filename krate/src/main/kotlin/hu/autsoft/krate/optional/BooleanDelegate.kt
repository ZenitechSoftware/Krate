package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class BooleanDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, Boolean?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getBoolean(key, false)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putBoolean(key, value) }
        }
    }

}
