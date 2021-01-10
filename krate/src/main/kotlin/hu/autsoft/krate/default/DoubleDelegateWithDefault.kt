package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import hu.autsoft.krate.util.getDouble
import hu.autsoft.krate.util.putDouble
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class DoubleDelegateWithDefault(
        private val key: String,
        private val default: Double
) : ReadWriteProperty<Krate, Double> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Double {
        return if (thisRef.sharedPreferences.contains(key)) {
            thisRef.sharedPreferences.getDouble(key, default)
        } else {
            default
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Double) {
        thisRef.sharedPreferences.edit { putDouble(key, value) }
    }
}