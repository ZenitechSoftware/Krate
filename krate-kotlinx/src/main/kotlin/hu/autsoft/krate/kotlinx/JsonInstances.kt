package hu.autsoft.krate.kotlinx

import hu.autsoft.krate.Krate
import kotlinx.serialization.json.Json

internal inline val defaultJson: Json
    get() = Json.Default

internal val jsonInstances: MutableMap<Krate, Json> = mutableMapOf<Krate, Json>().withDefault { defaultJson }

internal inline val Krate.internalJson: Json
    get() = jsonInstances.getValue(this)

/**
 * The Json instance used for [kotlinxPref]s for the given Krate.
 *
 * Set to null to use the default instance.
 */
public var Krate.json: Json?
    set(value) {
        if (value != null) {
            jsonInstances[this] = value
        } else {
            jsonInstances.remove(this)
        }
    }
    get() {
        return jsonInstances[this]
    }
