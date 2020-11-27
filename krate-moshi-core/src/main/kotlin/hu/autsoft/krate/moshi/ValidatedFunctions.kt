@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.moshi

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.moshi.default.MoshiDelegateWithDefault
import hu.autsoft.krate.moshi.optional.MoshiDelegate
import hu.autsoft.krate.validation.ValidatedPreferenceDelegate
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

/**
 * Creates a validated, optional preference of type T with the given [key] in this [Krate] instance.
 * This value will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
        key: String,
        noinline isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return moshiPrefImpl(key, typeOf<T>().javaType, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
        key: String,
        type: Type,
        isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return ValidatedPreferenceDelegate(MoshiDelegate(key, type), isValid)
}

/**
 * Creates a validated, non-optional preference of type T with the given [key] and [defaultValue]
 * in this [Krate] instance.
 * This value will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
        key: String,
        defaultValue: T,
        noinline isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return moshiPrefImpl(key, defaultValue, typeOf<T>().javaType, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
        key: String,
        defaultValue: T,
        type: Type,
        isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return ValidatedPreferenceDelegate(MoshiDelegateWithDefault(key, defaultValue, type), isValid)
}
