package hu.autsoft.krate.gson.default

import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private class GsonDelegateWithDefault<T : Any>(
    private val key: String,
    private val default: T,
    private val adapter: TypeAdapter<T>,
) : ReadWriteProperty<Krate, T> {
    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T {
        if (!thisRef.sharedPreferences.contains(key)) {
            return default
        }

        val string = thisRef.sharedPreferences.getString(key, null)
        return adapter.fromJson(string)
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit {
            putString(key, adapter.toJson(value))
        }
    }
}

internal class GsonDelegateWithDefaultFactory<T : Any>(
    private val key: String,
    private val default: T,
    private val type: Type,
) : PropertyDelegateProvider<Krate, ReadWriteProperty<Krate, T>> {
    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): ReadWriteProperty<Krate, T> {
        @Suppress("UNCHECKED_CAST")
        val adapter: TypeAdapter<T> = thisRef.internalGson.getAdapter(TypeToken.get(type)) as TypeAdapter<T>
        return GsonDelegateWithDefault(key, default, adapter)
    }
}
