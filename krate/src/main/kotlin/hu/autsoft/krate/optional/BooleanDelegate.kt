package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class BooleanDelegate(
    override val key: String,
) : KeyedKrateProperty<Boolean?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getBoolean(key, false)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putBoolean(key, value) }
        }
    }

}
