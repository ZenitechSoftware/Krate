package hu.autsoft.krate.moshi.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.moshi.realMoshiInstance
import hu.autsoft.krate.moshi.util.edit
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


internal class MoshiDelegate<T : Any>(
        private val key: String,
        private val type: Type,
) : ReadWriteProperty<Krate, T?> {

    override fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
            val adapter = thisRef.realMoshiInstance.adapter<T?>(type)
            adapter.fromJson(string)
        }
    }

    override fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            thisRef.sharedPreferences.edit {
                val adapter = thisRef.realMoshiInstance.adapter<T?>(type)
                putString(key, adapter.toJson(value))
            }
        }
    }

}
