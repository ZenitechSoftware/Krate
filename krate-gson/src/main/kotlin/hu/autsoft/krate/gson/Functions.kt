@file:Suppress("RedundantVisibilityModifier")

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.default.GsonDelegateWithDefault
import hu.autsoft.krate.gson.optional.GsonDelegate
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(key: String): ReadWriteProperty<Krate, T?> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(key: String, type: Type): ReadWriteProperty<Krate, T?> {
    return GsonDelegate(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(key: String, defaultValue: T): ReadWriteProperty<Krate, T> {
    return gsonPrefImpl(key, defaultValue, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(key: String, defaultValue: T, type: Type): ReadWriteProperty<Krate, T> {
    return GsonDelegateWithDefault(key, defaultValue, type)
}
