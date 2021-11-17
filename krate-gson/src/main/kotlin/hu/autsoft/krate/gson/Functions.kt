@file:Suppress("unused")

package hu.autsoft.krate.gson

import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.gson.optional.GsonDelegateFactory
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type T with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This instance will be serialized using Gson.
 */
public inline fun <reified T : Any> Krate.gsonPref(
    key: String? = null,
): KeyedKratePropertyProvider<T?> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> Krate.gsonPrefImpl(
    key: String?,
    type: Type,
): KeyedKratePropertyProvider<T?> {
    return GsonDelegateFactory(key, type)
}

/**
 * Creates a non-optional preference of type T with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 * This instance will be serialized using Gson.
 */
@Deprecated(
    message = "Use .withDefault() on a gsonPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.gsonPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public inline fun <reified T : Any> Krate.gsonPref(
    key: String? = null,
    defaultValue: T,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    return gsonPref<T>(key).withDefault(defaultValue)
}
