package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class StringDelegate(
    override val key: String?,
) : KeyedKrateProperty<String?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): String? {
        return if (!thisRef.sharedPreferences.contains(key ?: property.name)) {
            null
        } else {
            thisRef.sharedPreferences.getString(key ?: property.name, null)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: String?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key ?: property.name) }
        } else {
            thisRef.sharedPreferences.edit { putString(key ?: property.name, value) }
        }
    }

}
