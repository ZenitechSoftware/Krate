@file:Suppress("RedundantVisibilityModifier")

package hu.autsoft.krate

import hu.autsoft.krate.default.*
import hu.autsoft.krate.optional.*
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type Boolean with the given [key] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String): ReadWriteProperty<Krate, Boolean?> {
    return BooleanDelegate(sharedPreferences, key)
}

/**
 * Creates an optional preference of type Float with the given [key] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String): ReadWriteProperty<Krate, Float?> {
    return FloatDelegate(sharedPreferences, key)
}

/**
 * Creates an optional preference of type Int with the given [key] in this [Krate] instance.
 */
public fun Krate.intPref(key: String): ReadWriteProperty<Krate, Int?> {
    return IntDelegate(sharedPreferences, key)
}

/**
 * Creates an optional preference of type Long with the given [key] in this [Krate] instance.
 */
public fun Krate.longPref(key: String): ReadWriteProperty<Krate, Long?> {
    return LongDelegate(sharedPreferences, key)
}

/**
 * Creates an optional preference of type String with the given [key] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String): ReadWriteProperty<Krate, String?> {
    return StringDelegate(sharedPreferences, key)
}

/**
 * Creates an optional preference of type Set<String> with the given [key] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String): ReadWriteProperty<Krate, Set<String>?> {
    return StringSetDelegate(sharedPreferences, key)
}


/**
 * Creates a non-optional preference of type Boolean with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String, defaultValue: Boolean): ReadWriteProperty<Krate, Boolean> {
    return BooleanDelegateWithDefault(sharedPreferences, key, defaultValue)
}

/**
 * Creates a non-optional preference of type Float with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String, defaultValue: Float): ReadWriteProperty<Krate, Float> {
    return FloatDelegateWithDefault(sharedPreferences, key, defaultValue)
}

/**
 * Creates a non-optional preference of type Int with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.intPref(key: String, defaultValue: Int): ReadWriteProperty<Krate, Int> {
    return IntDelegateWithDefault(sharedPreferences, key, defaultValue)
}

/**
 * Creates a non-optional preference of type Long with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.longPref(key: String, defaultValue: Long): ReadWriteProperty<Krate, Long> {
    return LongDelegateWithDefault(sharedPreferences, key, defaultValue)
}

/**
 * Creates a non-optional preference of type String with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String, defaultValue: String): ReadWriteProperty<Krate, String> {
    return StringDelegateWithDefault(sharedPreferences, key, defaultValue)
}

/**
 * Creates a non-optional preference of type Set<String> with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String, defaultValue: Set<String>): ReadWriteProperty<Krate, Set<String>> {
    return StringSetDelegateWithDefault(sharedPreferences, key, defaultValue)
}
