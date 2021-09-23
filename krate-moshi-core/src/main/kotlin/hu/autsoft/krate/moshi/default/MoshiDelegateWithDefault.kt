@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.moshi.default

import com.squareup.moshi.JsonAdapter
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.base.KeyDelegateProvider
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.moshi.realMoshiInstance
import hu.autsoft.krate.moshi.util.edit
import java.lang.reflect.Type
import kotlin.reflect.KProperty


private class MoshiDelegateWithDefault<T : Any>(
    key: String,
    private val default: T,
    private val adapter: JsonAdapter<T>,
) : KeyDelegate<T>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }
        val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
        return requireNotNull(adapter.fromJson(string))
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            putString(key, adapter.toJson(value))
        }
    }
}

internal class MoshiDelegateWithDefaultFactory<T : Any>(
    private val key: String,
    private val default: T,
    private val type: Type,
) : KeyDelegateProvider<T>() {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyDelegate<T> {
        val adapter = thisRef.realMoshiInstance.adapter<T>(type)
        return MoshiDelegateWithDefault(key, default, adapter)
    }
}
