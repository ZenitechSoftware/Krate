@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.moshi

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.moshi.default.MoshiDelegateWithDefaultFactory
import hu.autsoft.krate.moshi.optional.MoshiDelegateFactory
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
    key: String,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return moshiPrefImpl(key, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
    key: String,
    type: Type,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return MoshiDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
    key: String,
    defaultValue: T,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return moshiPrefImpl(key, defaultValue, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
    key: String,
    defaultValue: T,
    type: Type,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return MoshiDelegateWithDefaultFactory(key, defaultValue, type)
}
