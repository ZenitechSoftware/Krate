@file:Suppress("RedundantVisibilityModifier")

package hu.autsoft.krate

import hu.autsoft.krate.default.BooleanDelegateWithDefault
import hu.autsoft.krate.default.FloatDelegateWithDefault
import hu.autsoft.krate.default.IntDelegateWithDefault
import hu.autsoft.krate.default.LongDelegateWithDefault
import hu.autsoft.krate.default.StringDelegateWithDefault
import hu.autsoft.krate.default.StringSetDelegateWithDefault
import hu.autsoft.krate.optional.BooleanDelegate
import hu.autsoft.krate.optional.FloatDelegate
import hu.autsoft.krate.optional.IntDelegate
import hu.autsoft.krate.optional.LongDelegate
import hu.autsoft.krate.optional.StringDelegate
import hu.autsoft.krate.optional.StringSetDelegate
import kotlin.properties.ReadWriteProperty

/**
 * Creates an optional preference of type Boolean with the given [key] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String): ReadWriteProperty<Krate, Boolean?> {
    return BooleanDelegate(key)
}

/**
 * Creates an optional preference of type Float with the given [key] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String): ReadWriteProperty<Krate, Float?> {
    return FloatDelegate(key)
}

/**
 * Creates an optional preference of type Int with the given [key] in this [Krate] instance.
 */
public fun Krate.intPref(key: String): ReadWriteProperty<Krate, Int?> {
    return IntDelegate(key)
}

/**
 * Creates an optional preference of type Long with the given [key] in this [Krate] instance.
 */
public fun Krate.longPref(key: String): ReadWriteProperty<Krate, Long?> {
    return LongDelegate(key)
}

/**
 * Creates an optional preference of type String with the given [key] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String): ReadWriteProperty<Krate, String?> {
    return StringDelegate(key)
}

/**
 * Creates an optional preference of type Set<String> with the given [key] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String): ReadWriteProperty<Krate, Set<String>?> {
    return StringSetDelegate(key)
}


/**
 * Creates a non-optional preference of type Boolean with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.booleanPref(key: String, defaultValue: Boolean): ReadWriteProperty<Krate, Boolean> {
    return BooleanDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type Float with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.floatPref(key: String, defaultValue: Float): ReadWriteProperty<Krate, Float> {
    return FloatDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type Int with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.intPref(key: String, defaultValue: Int): ReadWriteProperty<Krate, Int> {
    return IntDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type Long with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.longPref(key: String, defaultValue: Long): ReadWriteProperty<Krate, Long> {
    return LongDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type String with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringPref(key: String, defaultValue: String): ReadWriteProperty<Krate, String> {
    return StringDelegateWithDefault(key, defaultValue)
}

/**
 * Creates a non-optional preference of type Set<String> with the given [key] and [defaultValue] in this [Krate] instance.
 */
public fun Krate.stringSetPref(key: String, defaultValue: Set<String>): ReadWriteProperty<Krate, Set<String>> {
    return StringSetDelegateWithDefault(key, defaultValue)
}
