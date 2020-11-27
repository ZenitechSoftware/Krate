@file:Suppress("unused")

package hu.autsoft.krate.kotlinx

import hu.autsoft.krate.Krate
import hu.autsoft.krate.kotlinx.default.KotlinxDelegateWithDefault
import hu.autsoft.krate.kotlinx.optional.KotlinxDelegate
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * This instance will be serialized using .
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(key: String): ReadWriteProperty<Krate, T?> {
    return kotlinxPrefImpl(key, typeOf<T>())
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(key: String, type: KType): ReadWriteProperty<Krate, T?> {
    return KotlinxDelegate(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * This instance will be serialized using kotlinx.serialization.
 */
@OptIn(ExperimentalStdlibApi::class)
public inline fun <reified T : Any> Krate.kotlinxPref(key: String, defaultValue: T): ReadWriteProperty<Krate, T> {
    return kotlinxPrefImpl(key, defaultValue, typeOf<T>())
}

@PublishedApi
internal fun <T : Any> Krate.kotlinxPrefImpl(key: String, defaultValue: T, type: KType): ReadWriteProperty<Krate, T> {
    return KotlinxDelegateWithDefault(key, defaultValue, type)
}
