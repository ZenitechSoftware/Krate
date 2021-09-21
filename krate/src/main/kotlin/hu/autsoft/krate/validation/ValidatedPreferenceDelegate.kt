package hu.autsoft.krate.validation

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A generic [ReadWriteProperty] that can be used to validate values that are being set
 * into another delegate.
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
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

@InternalKrateApi
public class ValidatedPreferenceDelegateFactory<T>(
    private val propertyDelegateProvider: PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>>,
    private val isValid: (newValue: T) -> Boolean,
) : PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): ReadWriteProperty<Krate, T> {
        return ValidatedPreferenceDelegate(
            delegate = propertyDelegateProvider.provideDelegate(thisRef, property),
            isValid = isValid,
        )
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

/**
 * Adds validation to a Krate delegate factory.
 *
 * If a value being set to this preference returns `false` when checked by [isValid],
 * an [IllegalArgumentException] will be thrown.
 *
 * Example property using this function for validation:
 *
 * ```kotlin
 * var validatedModel by kotlinxPref("validatedModel")
 *      .validate { newValue ->
 *          newValue.x > 3 // arbitrary condition
 *      }
 * ```
 */
public fun <T : Any?> PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>>.validate(
    isValid: (newValue: T) -> Boolean,
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    @OptIn(InternalKrateApi::class)
    return ValidatedPreferenceDelegateFactory(this, isValid)
}
