package hu.autsoft.krate.moshi

import com.squareup.moshi.Moshi
import hu.autsoft.krate.Krate

internal val moshiInstances: MutableMap<Krate, Moshi> = mutableMapOf<Krate, Moshi>().withDefault {
    try {
        defaultMoshi
    } catch (e: Throwable) {
        throw IllegalStateException(
            "Do not use krate-moshi-core directly. Either krate-moshi-codegen (if you only use Moshi" +
                    " with codegen) or krate-moshi-reflect (if you use Moshi via reflection at all) should be" +
                    " included as a dependency!",
            e
        )
    }
}

/**
 * This read-only property fetches the Moshi instance used for serialization with this given Krate.
 *
 * This will always return a Moshi instance. If no custom one is set, it will return the default
 * instance that's being used internally.
 *
 * If you want to set a custom Moshi instance, see the [moshi] property.
 */
public val Krate.realMoshiInstance: Moshi
    get() = moshiInstances.getValue(this)

/**
 * The custom Moshi instance used for [moshiPref]s for the given Krate.
 *
 * When read, this property returns either the custom instance if one is set, or null.
 *
 * Set to null to use the default instance.
 */
public var Krate.moshi: Moshi?
    set(value) {
        if (value != null) {
            moshiInstances[this] = value
        } else {
            moshiInstances.remove(this)
        }
    }
    get() {
        return moshiInstances[this]
    }
