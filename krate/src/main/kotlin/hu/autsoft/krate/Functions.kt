@file:Suppress("unused")

package hu.autsoft.krate

import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.optional.BooleanDelegate
import hu.autsoft.krate.optional.FloatDelegate
import hu.autsoft.krate.optional.IntDelegate
import hu.autsoft.krate.optional.LongDelegate
import hu.autsoft.krate.optional.StringDelegate
import hu.autsoft.krate.optional.StringSetDelegate
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type [Boolean] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.booleanPref(key: String? = null): KeyedKrateProperty<Boolean?> {
    return BooleanDelegate(key)
}

/**
 * Creates an optional preference of type [Float] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.floatPref(key: String? = null): KeyedKrateProperty<Float?> {
    return FloatDelegate(key)
}

/**
 * Creates an optional preference of type [Int] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.intPref(key: String? = null): KeyedKrateProperty<Int?> {
    return IntDelegate(key)
}

/**
 * Creates an optional preference of type [Long] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.longPref(key: String? = null): KeyedKrateProperty<Long?> {
    return LongDelegate(key)
}

/**
 * Creates an optional preference of type [String] with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.stringPref(key: String? = null): KeyedKrateProperty<String?> {
    return StringDelegate(key)
}

/**
 * Creates an optional preference of type Set<String> with the given [key] in this [Krate] instance.
 * If [key] is `null` then the property name will be used as key.
 */
public fun Krate.stringSetPref(key: String? = null): KeyedKrateProperty<Set<String>?> {
    return StringSetDelegate(key)
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
public fun Krate.booleanPref(key: String? = null, defaultValue: Boolean): ReadWriteProperty<Krate, Boolean> {
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
public fun Krate.floatPref(key: String? = null, defaultValue: Float): ReadWriteProperty<Krate, Float> {
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
public fun Krate.intPref(key: String? = null, defaultValue: Int): ReadWriteProperty<Krate, Int> {
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
public fun Krate.longPref(key: String? = null, defaultValue: Long): ReadWriteProperty<Krate, Long> {
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
public fun Krate.stringPref(key: String? = null, defaultValue: String): ReadWriteProperty<Krate, String> {
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
public fun Krate.stringSetPref(key: String? = null, defaultValue: Set<String>): ReadWriteProperty<Krate, Set<String>> {
    return stringSetPref(key).withDefault(defaultValue)
}
