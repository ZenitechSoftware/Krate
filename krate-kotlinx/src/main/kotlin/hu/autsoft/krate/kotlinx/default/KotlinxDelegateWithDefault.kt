@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.kotlinx.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.base.KeyDelegateProvider
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.kotlinx.internalJson
import hu.autsoft.krate.kotlinx.util.edit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.KProperty
import kotlin.reflect.KType

private class KotlinxDelegateWithDefault<T : Any>(
    key: String,
    private val default: T,
    private val serializer: KSerializer<T>,
) : KeyDelegate<T>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }
        val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
        return thisRef.internalJson.decodeFromString(serializer, string)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            putString(key, thisRef.internalJson.encodeToString(serializer, value))
        }
    }
}

internal class KotlinxDelegateWithDefaultFactory<T : Any>(
    private val key: String,
    private val default: T,
    private val type: KType,
) : KeyDelegateProvider<T>() {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyDelegate<T> {
        @Suppress("UNCHECKED_CAST")
        val serializer = thisRef.internalJson.serializersModule.serializer(type) as KSerializer<T>
        return KotlinxDelegateWithDefault(key, default, serializer)
    }
}
