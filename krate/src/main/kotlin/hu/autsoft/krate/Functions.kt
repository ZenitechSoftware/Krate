@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate

import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.default.*
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.optional.*

/**
 * Creates an optional preference of type [Boolean] with the given [key] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String): KeyDelegate<Boolean?> {
    return BooleanDelegate(key)
}

/**
 * Creates an optional preference of type [Float] with the given [key] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String): KeyDelegate<Float?> {
    return FloatDelegate(key)
}

/**
 * Creates an optional preference of type [Int] with the given [key] in this [Krate] instance.
 */
public fun Krate.intPref(key: String): KeyDelegate<Int?> {
    return IntDelegate(key)
}

/**
 * Creates an optional preference of type [Long] with the given [key] in this [Krate] instance.
 */
public fun Krate.longPref(key: String): KeyDelegate<Long?> {
    return LongDelegate(key)
}

/**
 * Creates an optional preference of type [String] with the given [key] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String): KeyDelegate<String?> {
    return StringDelegate(key)
}

/**
 * Creates an optional preference of type Set<String> with the given [key] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String): KeyDelegate<Set<String>?> {
    return StringSetDelegate(key)
}


/**
 * Creates a non-optional preference of type [Boolean] with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String, defaultValue: Boolean): KeyDelegate<Boolean> {
    return BooleanDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type [Float] with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String, defaultValue: Float): KeyDelegate<Float> {
    return FloatDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type [Int] with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.intPref(key: String, defaultValue: Int): KeyDelegate<Int> {
    return IntDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type [Long] with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.longPref(key: String, defaultValue: Long): KeyDelegate<Long> {
    return LongDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type [String] with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String, defaultValue: String): KeyDelegate<String> {
    return StringDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type Set<String> with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String, defaultValue: Set<String>): KeyDelegate<Set<String>> {
    return StringSetDelegateWithDefault(key, defaultValue)
}
