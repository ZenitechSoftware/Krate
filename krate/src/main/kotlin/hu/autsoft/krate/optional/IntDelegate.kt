package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class IntDelegate(
    override val key: String,
) : KeyedKrateProperty<Int?> {
    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Int? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getInt(key, 0)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Int?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putInt(key, value) }
        }
    }
}

internal class IntDelegateProvider(
    val key: String?,
) : KeyedKratePropertyProvider<Int?> {
    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): IntDelegate {
        return IntDelegate(key ?: property.name)
    }
}
