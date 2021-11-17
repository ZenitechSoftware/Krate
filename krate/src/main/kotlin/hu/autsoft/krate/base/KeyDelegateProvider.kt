package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.PropertyDelegateProvider

public interface KeyDelegateProvider<T> : PropertyDelegateProvider<Krate, KeyDelegate<T>>
