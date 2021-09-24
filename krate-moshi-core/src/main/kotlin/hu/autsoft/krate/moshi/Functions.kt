@file:Suppress("unused")

package hu.autsoft.krate.moshi

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegateProvider
import hu.autsoft.krate.default.DelegateWithDefaultFactory
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.moshi.optional.MoshiDelegateFactory
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This instance will be serialized using Moshi.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
    key: String? = null,
): KeyDelegateProvider<T?> {
    return moshiPrefImpl(key, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
    key: String? = null,
    type: Type,
): KeyDelegateProvider<T?> {
    return MoshiDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This instance will be serialized using Moshi.
 */
@Deprecated(
    message = "Use .withDefault() on a moshiPref instead",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith(
        "this.moshiPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.moshiPref(
    key: String? = null,
    defaultValue: T,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return moshiPrefImpl(key, defaultValue, typeOf<T>().javaType)
}

@PublishedApi
internal fun <T : Any> Krate.moshiPrefImpl(
    key: String? = null,
    defaultValue: T,
    type: Type,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    @OptIn(InternalKrateApi::class)
    return DelegateWithDefaultFactory(MoshiDelegateFactory(key, type), defaultValue)
}
