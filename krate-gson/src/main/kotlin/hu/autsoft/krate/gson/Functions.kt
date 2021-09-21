@file:Suppress("unused")

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.default.GsonDelegateWithDefaultFactory
import hu.autsoft.krate.gson.optional.GsonDelegateFactory
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
    key: String,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T?>> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String,
    type: Type,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T?>> {
    return GsonDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
    key: String,
    defaultValue: T,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return gsonPrefImpl(key, defaultValue, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String,
    defaultValue: T,
    type: Type,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return GsonDelegateWithDefaultFactory(key, defaultValue, type)
}
