package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.ReadWriteProperty

public abstract class KeyDelegate<T>(public val key: String) : ReadWriteProperty<Krate, T>