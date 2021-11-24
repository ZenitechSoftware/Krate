@file:Suppress("unused")

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wraps [delegate] and returns [defaultValue] when the underlying delegate's
 * value is accessed, but the SharedPreferences instead does not contain the
 * associated key.
 */
@InternalKrateApi
public class DelegateWithDefault<T>(
    private val delegate: KeyedKrateProperty<T?>,
    private val defaultValue: T,
) : ReadWriteProperty<Krate, T> {
    override fun getValue(thisRef: Krate, property: KProperty<*>): T {
        return if (delegate.key !in thisRef.sharedPreferences) {
            defaultValue
        } else {
            delegate.getValue(thisRef, property)!!
        }
    }

    override fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        delegate.setValue(thisRef, property, value)
    }
}

/**
 * Wraps the delegate provided by [propertyDelegateProvider] into a [DelegateWithDefault].
 */
@InternalKrateApi
public class DelegateWithDefaultFactory<T>(
    private val propertyDelegateProvider: PropertyDelegateProvider<Krate, KeyedKrateProperty<T?>>,
    private val defaultValue: T,
) : PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): ReadWriteProperty<Krate, T> {
        return DelegateWithDefault(
            delegate = propertyDelegateProvider.provideDelegate(thisRef, property),
            defaultValue = defaultValue
        )
    }
}

/**
 * Adds a default value to a Krate delegate: if a Krate property's value
 * not been set before, [defaultValue] is returned instead of `null`.
 *
 * Example property using this function:
 *
 * ```kotlin
 * var username by stringPref("username").withDefault("Default User")
 * ```
 */
public fun <T : Any?> KeyedKrateProperty<T?>.withDefault(
    defaultValue: T
): ReadWriteProperty<Krate, T> {
    @OptIn(InternalKrateApi::class)
    return DelegateWithDefault(this, defaultValue)
}

/**
 * Adds a default value to a Krate delegate: if a Krate property's value
 * not been set before, [defaultValue] is returned instead of `null`.
 *
 * Example property using this function:
 *
 * ```kotlin
 * var user by moshiPref("user").withDefault(User("Guest"))
 * ```
 */
public fun <T : Any?> KeyedKratePropertyProvider<T?>.withDefault(
    defaultValue: T
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    @OptIn(InternalKrateApi::class)
    return DelegateWithDefaultFactory(this, defaultValue)
}
