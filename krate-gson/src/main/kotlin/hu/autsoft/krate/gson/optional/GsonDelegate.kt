package hu.autsoft.krate.gson.optional

import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.reflect.KProperty

private class GsonDelegate<T : Any>(
    override val key: String,
    private val adapter: TypeAdapter<T>,
) : KeyedKrateProperty<T?> {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
            adapter.fromJson(string)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            thisRef.sharedPreferences.edit {
                putString(key, adapter.toJson(value))
            }
        }
    }
}

internal class GsonDelegateFactory<T : Any>(
    private val key: String?,
    private val type: Type,
) : KeyedKratePropertyProvider<T?> {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyedKrateProperty<T?> {
        @Suppress("UNCHECKED_CAST")
        val adapter: TypeAdapter<T> = thisRef.internalGson.getAdapter(TypeToken.get(type)) as TypeAdapter<T>
        return GsonDelegate(key ?: property.name, adapter)
    }
}
