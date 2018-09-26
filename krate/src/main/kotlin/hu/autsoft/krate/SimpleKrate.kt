@file:Suppress("RedundantVisibilityModifier")

package hu.autsoft.krate

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

public abstract class SimpleKrate(
        context: Context,
        name: String? = null
) : Krate {

    public override val sharedPreferences: SharedPreferences = when (name) {
        null -> PreferenceManager.getDefaultSharedPreferences(context)
        else -> context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}
