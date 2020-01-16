package hu.autsoft.krate.moshi.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.moshi.realMoshiInstance
import hu.autsoft.krate.moshi.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class MoshiDelegateWithDefault<T : Any>(
        private val key: String,
        private val default: T,
        private val type: Type
) : ReadWriteProperty<Krate, T> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }

        val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
        val adapter = thisRef.realMoshiInstance.adapter<T>(type)
        return requireNotNull(adapter.fromJson(string))
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            val adapter = thisRef.realMoshiInstance.adapter<T>(type)
            putString(key, adapter.toJson(value))
        }
    }

}
