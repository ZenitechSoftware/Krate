@file:Suppress("unused")

package hu.autsoft.krate.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@InternalKrateApi
public class DelegateWithDefault<T>(
    private val delegate: KeyedKrateProperty<T?>,
    private val default: T,
) : ReadWriteProperty<Krate, T> {

    override fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        delegate.setValue(thisRef, property, value)
    }

    override fun getValue(thisRef: Krate, property: KProperty<*>): T {
        return if (!thisRef.sharedPreferences.contains(delegate.key)) default else delegate.getValue(thisRef, property)!!
    }
}

@InternalKrateApi
public class DelegateWithDefaultFactory<T>(
    private val propertyDelegateProvider: PropertyDelegateProvider<Krate, KeyedKrateProperty<T?>>,
    private val default: T,
) : PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): ReadWriteProperty<Krate, T> {
        return DelegateWithDefault(
            delegate = propertyDelegateProvider.provideDelegate(thisRef, property),
            default = default
        )
    }
}

/**
 * Adds a default value to a Krate delegate.
 *
 * If a delegate returns with null because it have not been set before, then this returns with [default]
 *
 * Example property using this function:
 *
 * ```kotlin
 * var defaultString by stringPref("validatedString").withDefault("default")
 * ```
 */
public fun <T : Any?> KeyedKrateProperty<T?>.withDefault(
    default: T
): ReadWriteProperty<Krate, T> {
    @OptIn(InternalKrateApi::class)
    return DelegateWithDefault(this, default)
}

/**
 * Adds a default value to a Krate delegate factory.
 *
 * If a delegate returns with null because it have not been set before, then this returns with [default]
 *
 * Example property using this function:
 *
 * ```kotlin
 * var defaultModel by kotlinxPref("defaultModel").withDefault(DefaultModel())
 * ```
 */
public fun <T : Any?> KeyedKratePropertyProvider<T?>.withDefault(
    default: T
): PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    @OptIn(InternalKrateApi::class)
    return DelegateWithDefaultFactory(this, default)
}
