@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.gson.default.GsonDelegateWithDefaultFactory
import hu.autsoft.krate.gson.optional.GsonDelegateFactory
import hu.autsoft.krate.internal.InternalKrateApi
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
    key: String,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String,
    type: Type,
): PropertyDelegateProvider<Krate, KeyDelegate<T?>> {
    return GsonDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
    key: String,
    defaultValue: T,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return gsonPrefImpl(key, defaultValue, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String,
    defaultValue: T,
    type: Type,
): PropertyDelegateProvider<Krate, KeyDelegate<T>> {
    return GsonDelegateWithDefaultFactory(key, defaultValue, type)
}
