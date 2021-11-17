@file:Suppress("unused")

package hu.autsoft.krate

import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.optional.BooleanDelegateProvider
import hu.autsoft.krate.optional.FloatDelegateProvider
import hu.autsoft.krate.optional.IntDelegateProvider
import hu.autsoft.krate.optional.LongDelegateProvider
import hu.autsoft.krate.optional.StringDelegateProvider
import hu.autsoft.krate.optional.StringSetDelegateProvider
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type [Boolean] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.booleanPref(key: String? = null): KeyedKratePropertyProvider<Boolean?> {
    return BooleanDelegateProvider(key)
}

/**
 * Creates an optional preference of type [Float] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.floatPref(key: String? = null): KeyedKratePropertyProvider<Float?> {
    return FloatDelegateProvider(key)
}

/**
 * Creates an optional preference of type [Int] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.intPref(key: String? = null): KeyedKratePropertyProvider<Int?> {
    return IntDelegateProvider(key)
}

/**
 * Creates an optional preference of type [Long] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.longPref(key: String? = null): KeyedKratePropertyProvider<Long?> {
    return LongDelegateProvider(key)
}

/**
 * Creates an optional preference of type [String] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.stringPref(key: String? = null): KeyedKratePropertyProvider<String?> {
    return StringDelegateProvider(key)
}

/**
 * Creates an optional preference of type Set<String> with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.stringSetPref(key: String? = null): KeyedKratePropertyProvider<Set<String>?> {
    return StringSetDelegateProvider(key)
}


/**
 * Creates a non-optional preference of type [Boolean] with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on a booleanPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.booleanPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.booleanPref(key: String? = null, defaultValue: Boolean): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, Boolean>> {
    return booleanPref(key).withDefault(defaultValue)
}

/**
 * Creates a non-optional preference of type [Float] with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on a floatPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.floatPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.floatPref(key: String? = null, defaultValue: Float): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, Float>> {
    return floatPref(key).withDefault(defaultValue)
}

/**
 * Creates a non-optional preference of type [Int] with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on an intPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.intPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.intPref(key: String? = null, defaultValue: Int): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, Int>> {
    return intPref(key).withDefault(defaultValue)
}

/**
 * Creates a non-optional preference of type [Long] with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on a longPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.longPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.longPref(key: String? = null, defaultValue: Long): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, Long>> {
    return longPref(key).withDefault(defaultValue)
}

/**
 * Creates a non-optional preference of type [String] with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on a stringPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.stringPref(key: String? = null, defaultValue: String): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, String>> {
    return stringPref(key).withDefault(defaultValue)
}

/**
 * Creates a non-optional preference of type Set<String> with the given [key] and [defaultValue] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
@Deprecated(
    message = "Use .withDefault() on a stringSetPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringSetPref(key).withDefault(defaultValue)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault"),
    ),
)
public fun Krate.stringSetPref(key: String? = null, defaultValue: Set<String>): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, Set<String>>> {
    return stringSetPref(key).withDefault(defaultValue)
}
