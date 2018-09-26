package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.util.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class StringDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: String
) : ReadWriteProperty<Krate, String> {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override operator fun getValue(thisRef: Krate, property: KProperty<*>): String {
        return sharedPreferences.getString(key, default)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

}
