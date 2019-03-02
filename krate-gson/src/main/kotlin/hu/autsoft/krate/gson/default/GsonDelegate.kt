package hu.autsoft.krate.gson.default

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class GsonDelegateWithDefault<T : Any>(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val default: T,
        private val type: Type
) : ReadWriteProperty<Krate, T> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!sharedPreferences.contains(key)) {
            return default
        }

        val string = sharedPreferences.getString(key, null)
        return thisRef.internalGson.fromJson(string, type)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        sharedPreferences.edit {
            putString(key, thisRef.internalGson.toJson(value))
        }
    }

}
