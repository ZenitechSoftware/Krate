package hu.autsoft.krate.gson.optional

import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class GsonDelegate<T : Any>(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val type: Type
) : ReadWriteProperty<Krate, T?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            val string = sharedPreferences.getString(key, null)
            thisRef.internalGson.fromJson(string, type)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            sharedPreferences.edit {
                remove(key)
            }
        } else {
            sharedPreferences.edit {
                putString(key, thisRef.internalGson.toJson(value))
            }
        }
    }

}
