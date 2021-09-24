@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.default.DelegateWithDefaultFactory
import hu.autsoft.krate.gson.optional.GsonDelegateFactory
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.validation.ValidatedPreferenceDelegateFactory
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty

/**
 * Creates a validated, optional preference of type T with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This value will be serialized using Gson.
 */
@Deprecated(
    message = "Use .validate {} on a gsonPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.gsonPref<T>(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public inline fun <reified T : Any> Krate.gsonPref(
    key: String? = null,
    noinline isValid: (newValue: T?) -> Boolean,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T?>> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String? = null,
    type: Type,
    isValid: (newValue: T?) -> Boolean,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T?>> {
    return ValidatedPreferenceDelegateFactory(GsonDelegateFactory(key, type), isValid)
}

/**
 * Creates a validated, non-optional preference of type T with the given [key] and [defaultValue]
 * in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This value will be serialized using Gson.
 */
@Deprecated(
    message = "Use .validate {} on a gsonPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.gsonPref(key, defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public inline fun <reified T : Any> Krate.gsonPref(
    key: String? = null,
    defaultValue: T,
    noinline isValid: (newValue: T) -> Boolean,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return gsonPrefImpl(key, defaultValue, object : TypeToken<T>() {}.type, isValid)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String? = null,
    defaultValue: T,
    type: Type,
    isValid: (newValue: T) -> Boolean,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return ValidatedPreferenceDelegateFactory(DelegateWithDefaultFactory(GsonDelegateFactory(key, type), defaultValue), isValid)
}
