@file:Suppress("RedundantVisibilityModifier")

package hu.autsoft.krate

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


/**
 * A simple example implementation of a [Krate].
 *
 * @constructor Creates a [Krate] instance, obtaining a [SharedPreferences] from [context]. If [name] is specified,
 * the [SharedPreferences] with that name will be used, otherwise the instance used is the one returned by
 * [PreferenceManager.getDefaultSharedPreferences].
 */
public abstract class SimpleKrate(
        context: Context,
        name: String? = null
) : Krate {

    public override val sharedPreferences: SharedPreferences = when (name) {
        null -> PreferenceManager.getDefaultSharedPreferences(context)
        else -> context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}
