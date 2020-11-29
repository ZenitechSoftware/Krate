package hu.autsoft.krate.kotlinx

import kotlinx.serialization.Serializable

@Serializable
internal data class TestModel(val x: Int, val y: Double, val z: String)
