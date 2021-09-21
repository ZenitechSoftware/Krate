package hu.autsoft.krate.kotlinx.default

import hu.autsoft.krate.Krate
import hu.autsoft.krate.kotlinx.internalJson
import hu.autsoft.krate.kotlinx.util.edit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

private class KotlinxDelegateWithDefault<T : Any>(
    private val key: String,
    private val default: T,
    private val serializer: KSerializer<T>,
) : ReadWriteProperty<Krate, T> {
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

internal class KotlinxDelegateWithDefaultFactory<T : Any>(
    private val key: String,
    private val default: T,
    private val type: KType,
) : PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): ReadWriteProperty<Krate, T> {
        @Suppress("UNCHECKED_CAST")
        val serializer = thisRef.internalJson.serializersModule.serializer(type) as KSerializer<T>
        return KotlinxDelegateWithDefault(key, default, serializer)
    }
}
