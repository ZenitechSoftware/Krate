package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class FloatDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Float
) : ReadWriteProperty<Krate, Float> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Float {
        return sharedPreferences.getFloat(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Float) {
        sharedPreferences.edit { putFloat(key, value) }
    }

}
