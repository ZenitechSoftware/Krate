@file:Suppress("RedundantVisibilityModifier", "unused")

package hu.autsoft.krate.moshi

import hu.autsoft.krate.Krate
import hu.autsoft.krate.moshi.default.MoshiDelegateWithDefault
import hu.autsoft.krate.moshi.optional.MoshiDelegate
import hu.autsoft.krate.moshi.util.makeType
import hu.autsoft.krate.validated.ValidatedPreferenceDelegate
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty

/**
 * Creates an validated, optional preference of type T with the given [key] in this [Krate] instance.
 * This value will be serialized using Moshi.
 */
public inline fun <reified T : Any> Krate.moshiPref(
        key: String,
        noinline isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return moshiPrefImpl(key, makeType<T>(), isValid)
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
public inline fun <reified T : Any> Krate.moshiPref(
        key: String,
        defaultValue: T,
        noinline isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return moshiPrefImpl(key, defaultValue, makeType<T>(), isValid)
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
