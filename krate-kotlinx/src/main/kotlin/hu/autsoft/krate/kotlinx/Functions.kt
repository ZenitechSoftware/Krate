@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.kotlinx

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.kotlinx.default.KotlinxDelegateWithDefaultFactory
import hu.autsoft.krate.kotlinx.optional.KotlinxDelegateFactory
import kotlin.properties.PropertyDelegateProvider
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(
    key: String,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return kotlinxPrefImpl(key, typeOf<T>())
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(
    key: String,
    type: KType,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return KotlinxDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(
    key: String,
    defaultValue: T,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return kotlinxPrefImpl(key, defaultValue, typeOf<T>())
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(
    key: String,
    defaultValue: T,
    type: KType,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return KotlinxDelegateWithDefaultFactory(key, defaultValue, type)
}
