package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.PropertyDelegateProvider

public interface KeyedKratePropertyProvider<T> : PropertyDelegateProvider<Krate, KeyedKrateProperty<T>>
