package hu.autsoft.krate.gson

import com.google.gson.Gson
import hu.autsoft.krate.Krate

internal val defaultGson = Gson()

internal val gsonInstances: MutableMap<Krate, Gson> = mutableMapOf<Krate, Gson>().withDefault { defaultGson }

internal inline val Krate.internalGson: Gson
    get() = gsonInstances.getValue(this)

/**
 * The Gson instance used for [gsonPref]s for the given Krate.
 *
 * Set to null to use the default instance.
 */
var Krate.gson: Gson?
    set(value) {
        if (value != null) {
            gsonInstances[this] = value
        } else {
            gsonInstances.remove(this)
        }
    }
    get() {
        return gsonInstances[this]
    }
