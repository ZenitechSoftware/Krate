package hu.autsoft.krate.gson.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class GsonDelegate<T : Any>(
        private val key: String,
        private val type: Type
) : ReadWriteProperty<Krate, T?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            val string = thisRef.sharedPreferences.getString(key, null)
            thisRef.internalGson.fromJson(string, type)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            thisRef.sharedPreferences.edit {
                putString(key, thisRef.internalGson.toJson(value))
            }
        }
    }

}
