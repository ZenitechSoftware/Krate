package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class FloatDelegate(
    override val key: String
) : KeyedKrateProperty<Float?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Float? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getFloat(key, 0f)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Float?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putFloat(key, value) }
        }
    }

}
