package hu.autsoft.krate.moshi.optional

import com.squareup.moshi.JsonAdapter
import hu.autsoft.krate.Krate
import hu.autsoft.krate.base.KeyedKrateProperty
import hu.autsoft.krate.base.KeyedKratePropertyProvider
import hu.autsoft.krate.moshi.realMoshiInstance
import hu.autsoft.krate.moshi.util.edit
import java.lang.reflect.Type
import kotlin.reflect.KProperty

private class MoshiDelegate<T : Any>(
    override val key: String,
    private val adapter: JsonAdapter<T>,
) : KeyedKrateProperty<T?> {

    override fun getValue(thisRef: Krate, property: KProperty<*>): T? {
        return if (key !in thisRef.sharedPreferences) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
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
                putString(key, adapter.toJson(value))
            }
        }
    }
}

internal class MoshiDelegateFactory<T : Any>(
    private val key: String?,
    private val type: Type,
) : KeyedKratePropertyProvider<T?> {

    override fun provideDelegate(thisRef: Krate, property: KProperty<*>): KeyedKrateProperty<T?> {
        val adapter = thisRef.realMoshiInstance.adapter<T?>(type)
        return MoshiDelegate(key ?: property.name, adapter)
    }
}
