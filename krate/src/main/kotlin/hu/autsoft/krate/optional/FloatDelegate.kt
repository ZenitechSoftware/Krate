package hu.autsoft.krate.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class FloatDelegate(
        private val sharedPreferences: SharedPreferences,
        private val key: String
) : ReadWriteProperty<Krate, Float?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Float? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getFloat(key, 0f)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Float?) {
        if (value == null) {
            sharedPreferences.edit { remove(key) }
        } else {
            sharedPreferences.edit { putFloat(key, value) }
        }
    }

}
