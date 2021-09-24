package hu.autsoft.krateexample.krates

import android.content.Context
import hu.autsoft.krate.*
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.gson.gsonPref
import hu.autsoft.krate.kotlinx.kotlinxPref
import hu.autsoft.krate.moshi.moshiPref
import hu.autsoft.krateexample.models.User

class ExampleSimpleKrate(context: Context) : SimpleKrate(context, NAME), ExampleSettings {

    companion object {
        private const val NAME = "example"
    }

    override var exampleBoolean by booleanPref("exampleBoolean").withDefault(false)
    override var exampleFloat by floatPref("exampleFloat").withDefault(0f)
    override var exampleInt by intPref("exampleInt").withDefault(0)
    override var exampleLong by longPref("exampleLong").withDefault(0L)
    override var exampleString by stringPref("exampleString").withDefault("")
    override var exampleStringSet by stringSetPref("exampleStringSet").withDefault(setOf())
    override var exampleUserGson by gsonPref<User>("exampleUserGson").withDefault(User("Gson", "Smith"))
    override var exampleUserKotlinX by kotlinxPref<User>("exampleUserKotlinx").withDefault(User("KotlinX", "Smith"))
    override var exampleUserMoshi by moshiPref<User>("exampleUserMoshi").withDefault(User("Moshi", "Smith"))
}
