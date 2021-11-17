package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.PropertyDelegateProvider

/**
 * A [PropertyDelegateProvider] that always provides [KeyedKrateProperty] instances.
 */
public interface KeyedKratePropertyProvider<T> : PropertyDelegateProvider<Krate, KeyedKrateProperty<T>>
