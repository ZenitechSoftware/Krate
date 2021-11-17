package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class StringSetDelegate(
    override val key: String,
) : KeyedKrateProperty<Set<String>?> {
    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Set<String>? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getStringSet(key, emptySet())
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Set<String>?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putStringSet(key, value) }
        }
    }
}

internal class StringSetDelegateProvider(
    val key: String?,
) : KeyedKratePropertyProvider<Set<String>?> {
    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): StringSetDelegate {
        return StringSetDelegate(key ?: property.name)
    }
}
