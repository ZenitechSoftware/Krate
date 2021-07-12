package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import hu.autsoft.krate.util.getDouble
import hu.autsoft.krate.util.putDouble
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class DoubleDelegate(
        private val key: String
) : ReadWriteProperty<Krate, Double?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Double? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getDouble(key, 0.0)
            //thisRef.sharedPreferences.getLong(key, 0.0.toRawBits()).toDouble()
        }
    }

    override fun setValue(thisRef: Krate, property: KProperty<*>, value: Double?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putDouble(key, value) }
            //thisRef.sharedPreferences.edit { putLong(key, value.toRawBits()) }
        }
    }
}