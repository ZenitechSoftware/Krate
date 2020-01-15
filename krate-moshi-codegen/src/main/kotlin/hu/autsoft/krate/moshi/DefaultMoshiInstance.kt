package hu.autsoft.krate.moshi

import com.squareup.moshi.Moshi

internal val defaultMoshi by lazy {
    Moshi.Builder()
            .build()
}
