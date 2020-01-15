package hu.autsoft.krate.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestModel(val x: Int, val y: Double, val z: String)
