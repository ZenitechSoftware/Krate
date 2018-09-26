package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class StringSetDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, Set<String>?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Set<String>? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getStringSet(key, emptySet())
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Set<String>?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putStringSet(key, value) }
        }
    }

}
