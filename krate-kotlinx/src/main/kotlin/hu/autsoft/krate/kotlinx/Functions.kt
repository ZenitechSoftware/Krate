@file:Suppress("unused")

package hu.autsoft.krate.kotlinx

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.kotlinx.optional.KotlinxDelegateFactory
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(
    key: String,
): KeyedKratePropertyProvider<T?> {
    return kotlinxPrefImpl(key, typeOf<T>())
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(
    key: String,
    type: KType,
): KeyedKratePropertyProvider<T?> {
    return KotlinxDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using kotlinx.serialization.
 */
@Deprecated(
    message = "Use .withDefault() on a kotlinxPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.kotlinxPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(
    key: String,
    defaultValue: T,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return kotlinxPref<T>(key).withDefault(defaultValue)
}
