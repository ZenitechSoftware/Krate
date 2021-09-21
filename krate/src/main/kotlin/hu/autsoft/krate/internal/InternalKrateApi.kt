package hu.autsoft.krate.internal

@RequiresOptIn(
    message = "This API is meant to be used by Krate's own modules, and third-party extensions to Krate. " +
            "If you're a client of the Krate library, you probably shouldn't be using it.",
    level = RequiresOptIn.Level.ERROR
)
public annotation class InternalKrateApi
