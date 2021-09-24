package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class BooleanDelegate(key: String?) : KeyDelegate<Boolean?>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean? {
        return if (!thisRef.sharedPreferences.contains(key ?: property.name)) {
            null
        } else {
            thisRef.sharedPreferences.getBoolean(key ?: property.name, false)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key ?: property.name) }
        } else {
            thisRef.sharedPreferences.edit { putBoolean(key ?: property.name, value) }
        }
    }

}
