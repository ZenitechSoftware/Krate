package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.ReadWriteProperty

public interface KeyedKrateProperty<T> : ReadWriteProperty<Krate, T> {
    public val key: String
}
