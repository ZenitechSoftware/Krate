@file:Suppress("unused")

package hu.autsoft.krate.moshi

import hu.autsoft.krate.Krate
import hu.autsoft.krate.moshi.default.MoshiDelegateWithDefault
import hu.autsoft.krate.moshi.optional.MoshiDelegate
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(key: String): ReadWriteProperty<Krate, T?> {
    return moshiPrefImpl(key, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(key: String, type: Type): ReadWriteProperty<Krate, T?> {
    return MoshiDelegate(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(key: String, defaultValue: T): ReadWriteProperty<Krate, T> {
    return moshiPrefImpl(key, defaultValue, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(key: String, defaultValue: T, type: Type): ReadWriteProperty<Krate, T> {
    return MoshiDelegateWithDefault(key, defaultValue, type)
}
