@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class IntDelegateWithDefault(
    key: String,
    private val default: Int,
) : KeyDelegate<Int>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Int {
        return thisRef.sharedPreferences.getInt(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Int) {
        thisRef.sharedPreferences.edit { putInt(key, value) }
    }

}
