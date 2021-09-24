@file:[Suppress("unused") OptIn(InternalKrateApi::class)]

package hu.autsoft.krate

import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.internal.InternalKrateApi
import hu.autsoft.krate.optional.*
import hu.autsoft.krate.validation.ValidatedPreferenceDelegate
import kotlin.properties.ReadWriteProperty

/**
 * Creates a validated, optional preference of type [Float] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a floatPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.floatPref(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.floatPref(
    key: String,
    isValid: (newValue: Float?) -> Boolean,
): ReadWriteProperty<Krate, Float?> {
    return ValidatedPreferenceDelegate(FloatDelegate(key), isValid)
}

/**
 * Creates a validated, non-optional preference of type [Float] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a floatPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.floatPref(key).withDefault(defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault", "hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.floatPref(
    key: String,
    defaultValue: Float,
    isValid: (newValue: Float) -> Boolean,
): ReadWriteProperty<Krate, Float> {
    return ValidatedPreferenceDelegate(FloatDelegate(key).withDefault(defaultValue), isValid)
}

/**
 * Creates a validated, optional preference of type [Int] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on an intPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.intPref(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.intPref(
    key: String,
    isValid: (newValue: Int?) -> Boolean,
): ReadWriteProperty<Krate, Int?> {
    return ValidatedPreferenceDelegate(IntDelegate(key), isValid)
}

/**
 * Creates a validated, non-optional preference of type [Int] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on an intPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.intPref(key).withDefault(defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault", "hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.intPref(
    key: String,
    defaultValue: Int,
    isValid: (newValue: Int) -> Boolean,
): ReadWriteProperty<Krate, Int> {
    return ValidatedPreferenceDelegate(IntDelegate(key).withDefault(defaultValue), isValid)
}

/**
 * Creates a validated, optional preference of type [Long] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a longPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.longPref(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.longPref(
    key: String,
    isValid: (newValue: Long?) -> Boolean,
): ReadWriteProperty<Krate, Long?> {
    return ValidatedPreferenceDelegate(LongDelegate(key), isValid)
}

/**
 * Creates a validated, non-optional preference of type [Long] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a longPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.longPref(key).withDefault(defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault", "hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.longPref(
    key: String,
    defaultValue: Long,
    isValid: (newValue: Long) -> Boolean,
): ReadWriteProperty<Krate, Long> {
    return ValidatedPreferenceDelegate(LongDelegate(key).withDefault(defaultValue), isValid)
}

/**
 * Creates a validated, optional preference of type [String] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a stringPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringPref(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.stringPref(
    key: String,
    isValid: (newValue: String?) -> Boolean,
): ReadWriteProperty<Krate, String?> {
    return ValidatedPreferenceDelegate(StringDelegate(key), isValid)
}

/**
 * Creates a validated, non-optional preference of type [String] with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a stringPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringPref(key).withDefault(defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault", "hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.stringPref(
    key: String,
    defaultValue: String,
    isValid: (newValue: String) -> Boolean,
): ReadWriteProperty<Krate, String> {
    return ValidatedPreferenceDelegate(StringDelegate(key).withDefault(defaultValue), isValid)
}

/**
 * Creates a validated, optional preference of type Set<String> with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a stringSetPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringSetPref(key).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.stringSetPref(
    key: String,
    isValid: (newValue: Set<String>?) -> Boolean,
): ReadWriteProperty<Krate, Set<String>?> {
    return ValidatedPreferenceDelegate(StringSetDelegate(key), isValid)
}

/**
 * Creates a validated, non-optional preference of type Set<String> with the given [key].
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 */
@Deprecated(
    message = "Use .validate {} on a stringSetPref instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "this.stringSetPref(key).withDefault(defaultValue).validate(isValid)",
        imports = arrayOf("hu.autsoft.krate.default.withDefault", "hu.autsoft.krate.validation.validate"),
    ),
)
public fun Krate.stringSetPref(
    key: String,
    defaultValue: Set<String>,
    isValid: (newValue: Set<String>) -> Boolean,
): ReadWriteProperty<Krate, Set<String>> {
    return ValidatedPreferenceDelegate(StringSetDelegate(key).withDefault(defaultValue), isValid)
}
