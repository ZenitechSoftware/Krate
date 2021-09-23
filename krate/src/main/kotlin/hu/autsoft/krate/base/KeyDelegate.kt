package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import hu.autsoft.krate.internal.InternalKrateApi
import kotlin.properties.ReadWriteProperty

@InternalKrateApi
public abstract class KeyDelegate<T>(public val key: String) : ReadWriteProperty<Krate, T>