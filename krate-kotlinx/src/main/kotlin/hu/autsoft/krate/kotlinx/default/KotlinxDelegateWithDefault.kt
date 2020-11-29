package hu.autsoft.krate.kotlinx.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.kotlinx.util.edit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType


internal class KotlinxDelegateWithDefault<T : Any>(
        private val key: String,
        private val default: T,
        private val type: KType,
) : ReadWriteProperty<Krate, T> {

    @Suppress("UNCHECKED_CAST")
    private inline val serializer: KSerializer<T>
        get() = Json.serializersModule.serializer(type) as KSerializer<T>

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }

        val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
        return Json.decodeFromString(serializer, string)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            putString(key, Json.encodeToString(serializer, value))
        }
    }

}
