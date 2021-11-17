package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.ReadWriteProperty

public interface KeyDelegate<T> : ReadWriteProperty<Krate, T> {
    public val key: String
}
