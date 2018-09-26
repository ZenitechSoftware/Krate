package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class IntDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Int
) : ReadWriteProperty<Krate, Int> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): Int {
        return sharedPreferences.getInt(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: Int) {
        sharedPreferences.edit { putInt(key, value) }
    }

}
