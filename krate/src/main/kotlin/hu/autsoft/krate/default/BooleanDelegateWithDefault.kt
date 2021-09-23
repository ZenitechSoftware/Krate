@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class BooleanDelegateWithDefault(
    key: String,
    private val default: Boolean,
) : KeyDelegate<Boolean>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Boolean {
        return thisRef.sharedPreferences.getBoolean(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Boolean) {
        thisRef.sharedPreferences.edit { putBoolean(key, value) }
    }

}
