package hu.autsoft.krate

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

abstract class SimpleKrate(
        context: Context,
        name: String? = null
) : Krate {

    override val sharedPreferences: SharedPreferences = when (name) {
        null -> PreferenceManager.getDefaultSharedPreferences(context)
        else -> context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}
