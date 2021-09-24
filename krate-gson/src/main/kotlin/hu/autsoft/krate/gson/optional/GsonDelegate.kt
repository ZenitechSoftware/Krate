package hu.autsoft.krate.gson.optional

import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyDelegate
import hu.autsoft.krate.base.KeyDelegateProvider
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.gson.util.edit
import java.lang.reflect.Type
import kotlin.reflect.KProperty

private class GsonDelegate<T : Any>(
    key: String?,
    private val adapter: TypeAdapter<T>,
) : KeyDelegate<T?>(key) {

    override operator fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (!thisRef.sharedPreferences.contains(key ?: property.name)) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key ?: property.name, null))
            adapter.fromJson(string)
        }
    }

    override operator fun setValue(thisRef: Krate, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key ?: property.name)
            }
        } else {
            thisRef.sharedPreferences.edit {
                putString(key ?: property.name, adapter.toJson(value))
            }
        }
    }
}

internal class GsonDelegateFactory<T : Any>(
    private val key: String?,
    private val type: Type,
) : KeyDelegateProvider<T?>() {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyDelegate<T?> {
        @Suppress("UNCHECKED_CAST")
        val adapter: TypeAdapter<T> = thisRef.internalGson.getAdapter(TypeToken.get(type)) as TypeAdapter<T>
        return GsonDelegate(key ?: property.name, adapter)
    }
}
