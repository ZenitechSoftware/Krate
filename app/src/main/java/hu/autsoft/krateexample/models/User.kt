package hu.autsoft.krateexample.models

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class User(
    val firstName: String,
    val lastName: String,
)
