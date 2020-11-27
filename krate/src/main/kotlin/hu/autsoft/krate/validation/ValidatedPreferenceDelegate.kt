package hu.autsoft.krate.validation

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [ValidatedPreferenceDelegate] is a generic [ReadWriteProperty] that can be used to
 * validate values that are being set.
 *
 * @param [delegate] the [ReadWriteProperty] implementation that is used for delegation
 * @param [isValid] the lambda used to validate property values on [setValue]
 */
@InternalKrateApi
public class ValidatedPreferenceDelegate<T>(
        private val delegate: ReadWriteProperty<Krate, T>,
        private val isValid: (newValue: T) -> Boolean,
) : ReadWriteProperty<Krate, T> by delegate {

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        require(isValid(value)) { "$value is not valid for ${property.name}." }

        delegate.setValue(thisRef, property, value)
    }

}

/**
 * Adds validation to a Krate delegate.
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 *
 * Example property using this function for validation:
 *
 * ```kotlin
 * var validatedString by stringPref("validatedString", "default")
 *      .validate { newValue ->
 *          newValue.length > 3 // arbitrary condition
 *      }
 * ```
 */
public fun <T : Any?> ReadWriteProperty<Krate, T>.validate(
        isValid: (newValue: T) -> Boolean,
): ReadWriteProperty<Krate, T> {
    @OptIn(InternalKrateApi::class)
    return ValidatedPreferenceDelegate(this, isValid)
}
