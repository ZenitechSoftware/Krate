@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.util.edit
import kotlin.reflect.KProperty

internal class StringSetDelegate(key: String) : KeyDelegate<Set<String>?>(key) {

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
