package hu.autsoft.krate.base

import hu.autsoft.krate.Krate
import kotlin.properties.ReadWriteProperty

/**
 * A Krate property delegate that holds the [key] to be used
 * for storing the value.
 */
public interface KeyedKrateProperty<T> : ReadWriteProperty<Krate, T> {
    public val key: String?
}
