package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class StringDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, String?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): String? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getString(key, null)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: String?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putString(key, value) }
        }
    }

}
