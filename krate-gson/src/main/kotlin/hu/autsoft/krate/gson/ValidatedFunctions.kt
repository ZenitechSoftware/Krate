@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.default.GsonDelegateWithDefault
import hu.autsoft.krate.gson.optional.GsonDelegate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.validated.ValidatedPreferenceDelegate
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty

/**
 * Creates an validated, optional preference of type T with the given [key] in this [Krate] instance.
 * This value will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
        key: String,
        noinline isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
        key: String,
        type: Type,
        isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return ValidatedPreferenceDelegate(GsonDelegate(key, type), isValid)
}

/**
 * Creates a validated, non-optional preference of type T with the given [key] and [defaultValue]
 * in this [Krate] instance.
 * This value will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
        key: String,
        defaultValue: T,
        noinline isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return gsonPrefImpl(key, defaultValue, object : TypeToken<T>() {}.type, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
        key: String,
        defaultValue: T,
        type: Type,
        isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return ValidatedPreferenceDelegate(GsonDelegateWithDefault(key, defaultValue, type), isValid)
}
