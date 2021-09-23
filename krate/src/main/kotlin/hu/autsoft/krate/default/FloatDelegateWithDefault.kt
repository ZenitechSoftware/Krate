@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class FloatDelegateWithDefault(
    key: String,
    private val default: Float,
) : KeyDelegate<Float>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Float {
        return thisRef.sharedPreferences.getFloat(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Float) {
        thisRef.sharedPreferences.edit { putFloat(key, value) }
    }

}
