package hu.autsoft.krate.moshi

import com.squareup.moshi.Moshi
import hu.autsoft.krate.internal.InternalKrateApi

@Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")
@InternalKrateApi
public val defaultMoshi: Moshi by lazy {
    TODO("""
        This is a compile time placeholder so that defaultMoshi can be resolved in the core module.
        You should include krate-moshi-reflect or krate-moshi-codegen on your classpath for runtime.
    """)
}
