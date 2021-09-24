package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.PropertyDelegateProvider

public abstract class KeyDelegateProvider<T> : PropertyDelegateProvider<Krate, KeyDelegate<T>>