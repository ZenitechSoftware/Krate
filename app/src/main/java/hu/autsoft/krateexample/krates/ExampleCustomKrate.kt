@file:Suppress("JoinDeclarationAndAssignment")

package hu.autsoft.krateexample.krates

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.doublePref
import hu.autsoft.krate.floatPref
import hu.autsoft.krate.intPref
import hu.autsoft.krate.longPref
import hu.autsoft.krate.stringPref
import hu.autsoft.krate.stringSetPref

class ExampleCustomKrate(context: Context) : Krate, ExampleSettings {

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences("custom_krate_prefs", Context.MODE_PRIVATE)
    }

    override var exampleBoolean by booleanPref("exampleBoolean", false)
    override var exampleDouble by doublePref("exampleDouble", 0.0)
    override var exampleFloat by floatPref("exampleFloat", 0f)
    override var exampleInt by intPref("exampleInt", 0)
    override var exampleLong by longPref("exampleLong", 0L)
    override var exampleString by stringPref("exampleString", "")
    override var exampleStringSet by stringSetPref("exampleStringSet", setOf())

}
