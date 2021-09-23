@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class LongDelegateWithDefault(
    key: String,
    private val default: Long,
) : KeyDelegate<Long>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Long {
        return thisRef.sharedPreferences.getLong(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Long) {
        thisRef.sharedPreferences.edit { putLong(key, value) }
    }

}
