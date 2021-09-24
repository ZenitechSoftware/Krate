package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class FloatDelegate(key: String?) : KeyDelegate<Float?>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Float? {
        return if (!thisRef.sharedPreferences.contains(key ?: property.name)) {
            null
        } else {
            thisRef.sharedPreferences.getFloat(key ?: property.name, 0f)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Float?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key ?: property.name) }
        } else {
            thisRef.sharedPreferences.edit { putFloat(key ?: property.name, value) }
        }
    }

}
