@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate.kotlinx

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.kotlinx.default.KotlinxDelegateWithDefault
import hu.autsoft.krate.kotlinx.optional.KotlinxDelegate
import hu.autsoft.krate.validation.ValidatedPreferenceDelegate
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Creates a validated, optional preference of type T with the given [key] in this [Krate] instance.
 * This value will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
@Deprecated(
        message = "Use .validate {} on a kotlinxPref instead",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
                "this.kotlinxPref<T>(key).validate(isValid)",
                imports = arrayOf("hu.autsoft.krate.validation.validate"),
        ),
)
public inline fun <reified T : Any> Krate.kotlinxPref(
        key: String,
        noinline isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return kotlinxPrefImpl(key, typeOf<T>(), isValid)
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(
        key: String,
        type: KType,
        isValid: (newValue: T?) -> Boolean
): ReadWriteProperty<Krate, T?> {
    return ValidatedPreferenceDelegate(KotlinxDelegate(key, type), isValid)
}

/**
 * Creates a validated, non-optional preference of type T with the given [key] and [defaultValue]
 * in this [Krate] instance.
 * This value will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
@Deprecated(
        message = "Use .validate {} on a kotlinxPref instead",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
                "this.kotlinxPref(key, defaultValue).validate(isValid)",
                imports = arrayOf("hu.autsoft.krate.validation.validate"),
        ),
)
public inline fun <reified T : Any> Krate.kotlinxPref(
        key: String,
        defaultValue: T,
        noinline isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return kotlinxPrefImpl(key, defaultValue, typeOf<T>(), isValid)
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(
        key: String,
        defaultValue: T,
        type: KType,
        isValid: (newValue: T) -> Boolean
): ReadWriteProperty<Krate, T> {
    return ValidatedPreferenceDelegate(KotlinxDelegateWithDefault(key, defaultValue, type), isValid)
}
