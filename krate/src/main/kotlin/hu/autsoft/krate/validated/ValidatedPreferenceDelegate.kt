package hu.autsoft.krate.validated

import hu.autsoft.krate.Krate
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [ValidatedPreferenceDelegate] is a generic [ReadWriteProperty] that can be used to
 * validate values that are being set.
 *
 * @param [delegate] the [ReadWriteProperty] implementation that is used for delegation
 * @param [isValid] the lambda used to validate property values on [setValue]
 */
class ValidatedPreferenceDelegate<T>(
        private val delegate: ReadWriteProperty<Krate, T>,
        private val isValid: (T) -> Boolean
) : ReadWriteProperty<Krate, T> by delegate {

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        if (!isValid(value)) {
            throw IllegalArgumentException("$value is not valid for ${property.name}.")
        }

        delegate.setValue(thisRef, property, value)
    }

}
