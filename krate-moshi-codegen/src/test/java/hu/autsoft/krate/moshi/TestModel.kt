package hu.autsoft.krate.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TestModel(val x: Int, val y: Double, val z: String)
