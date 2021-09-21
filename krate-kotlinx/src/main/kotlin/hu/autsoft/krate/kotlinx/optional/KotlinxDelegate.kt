package hu.autsoft.krate.kotlinx.optional

import hu.autsoft.krate.Krate
import hu.autsoft.krate.kotlinx.internalJson
import hu.autsoft.krate.kotlinx.util.edit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType


internal class KotlinxDelegate<T : Any>(
        private val key: String,
        private val type: KType,
) : ReadWriteProperty<Krate, T?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            @Suppress("UNCHECKED_CAST")
            val serializer = thisRef.internalJson.serializersModule.serializer(type) as KSerializer<T>
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
            Json.decodeFromString(serializer, string)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            @Suppress("UNCHECKED_CAST")
            val serializer = thisRef.internalJson.serializersModule.serializer(type) as KSerializer<T>
            thisRef.sharedPreferences.edit {
                putString(key, Json.encodeToString(serializer, value))
            }
        }
    }

}
