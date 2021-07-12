package hu.autsoft.krateexample.krates

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.doublePref
import hu.autsoft.krate.floatPref
import hu.autsoft.krate.intPref
import hu.autsoft.krate.longPref
import hu.autsoft.krate.stringPref
import hu.autsoft.krate.stringSetPref

class ExampleSimpleKrate(context: Context) : SimpleKrate(context, NAME), ExampleSettings {

    companion object {
        private const val NAME = "example"
    }

    override var exampleBoolean by booleanPref("exampleBoolean", false)
    override var exampleDouble by doublePref("exampleDouble", 0.0)
    override var exampleFloat by floatPref("exampleFloat", 0f)
    override var exampleInt by intPref("exampleInt", 0)
    override var exampleLong by longPref("exampleLong", 0L)
    override var exampleString by stringPref("exampleString", "")
    override var exampleStringSet by stringSetPref("exampleStringSet", setOf())

}
