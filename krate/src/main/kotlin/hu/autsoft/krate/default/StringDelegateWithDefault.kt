@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class StringDelegateWithDefault(
    key: String,
    private val default: String,
) : KeyDelegate<String>(key) {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override operator fun getValue(thisRef: Krate, property: KProperty<*>): String {
        return thisRef.sharedPreferences.getString(key, default)!!
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: String) {
        thisRef.sharedPreferences.edit { putString(key, value) }
    }

}
