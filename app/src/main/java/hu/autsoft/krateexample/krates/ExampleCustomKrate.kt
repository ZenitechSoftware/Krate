@file:Suppress("JoinDeclarationAndAssignment")

package hu.autsoft.krateexample.krates

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.floatPref
import hu.autsoft.krate.gson.gsonPref
import hu.autsoft.krate.intPref
import hu.autsoft.krate.kotlinx.kotlinxPref
import hu.autsoft.krate.longPref
import hu.autsoft.krate.moshi.moshiPref
import hu.autsoft.krate.stringPref
import hu.autsoft.krate.stringSetPref
import hu.autsoft.krateexample.models.User

class ExampleCustomKrate(context: Context) : Krate, ExampleSettings {

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences("custom_krate_prefs", Context.MODE_PRIVATE)
    }

    override var exampleBoolean by booleanPref("exampleBoolean").withDefault(false)
    override var exampleFloat by floatPref("exampleFloat").withDefault(0f)
    override var exampleInt by intPref("exampleInt").withDefault(0)
    override var exampleLong by longPref("exampleLong").withDefault(0L)
    override var exampleString by stringPref("exampleString").withDefault("")
    override var exampleStringSet by stringSetPref("exampleStringSet").withDefault(setOf())
    override var exampleUserGson by gsonPref<User>("user_gson").withDefault(User("Gson", "Green"))
    override var exampleUserKotlinX by kotlinxPref<User>("user_kotlinx").withDefault(User("KotlinX", "Klark"))
    override var exampleUserMoshi by moshiPref<User>("user_moshi").withDefault(User("Moshi", "Miller"))

}
