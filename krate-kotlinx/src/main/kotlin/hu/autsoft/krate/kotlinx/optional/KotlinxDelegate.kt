@file:OptIn(InternalKrateApi::class)

package hu.autsoft.krate.kotlinx.optional

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


private class KotlinxDelegate<T : Any>(
    key: String,
    private val serializer: KSerializer<T>,
) : KeyDelegate<T?>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
            thisRef.internalJson.decodeFromString(serializer, string)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            thisRef.sharedPreferences.edit {
                putString(key, thisRef.internalJson.encodeToString(serializer, value))
            }
        }
    }
}

internal class KotlinxDelegateFactory<T : Any>(
    private val key: String,
    private val type: KType,
) : KeyDelegateProvider<T?>() {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyDelegate<T?> {
        @Suppress("UNCHECKED_CAST")
        val serializer = thisRef.internalJson.serializersModule.serializer(type) as KSerializer<T>
        return KotlinxDelegate(key, serializer)
    }
}
