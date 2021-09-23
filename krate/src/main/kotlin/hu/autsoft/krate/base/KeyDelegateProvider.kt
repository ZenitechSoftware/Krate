package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.PropertyDelegateProvider

@InternalKrateApi
public abstract class KeyDelegateProvider<T> : PropertyDelegateProvider<Krate, KeyDelegate<T>>