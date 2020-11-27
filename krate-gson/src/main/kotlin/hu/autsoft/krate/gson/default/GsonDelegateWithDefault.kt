package hu.autsoft.krate.gson.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class GsonDelegateWithDefault<T : Any>(
        private val key: String,
        private val default: T,
        private val type: Type,
) : ReadWriteProperty<Krate, T> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }

        val string = thisRef.sharedPreferences.getString(key, null)
        return thisRef.internalGson.fromJson(string, type)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            putString(key, thisRef.internalGson.toJson(value))
        }
    }

}
