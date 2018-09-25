package hu.autsoft.krate.default

import android.content.SharedPreferences
import hu.autsoft.krate.edit
import kotlin.reflect.KProperty

class StringSetDelegateWithDefault(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: Set<String>
) {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> {
        return sharedPreferences.getStringSet(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
        sharedPreferences.edit { putStringSet(key, value) }
    }

}
