package hu.autsoft.krate

import android.content.SharedPreferences

/**
 * A container for [SharedPreferences] properties.
 */
public interface Krate {

    /**
     * The [SharedPreferences] instance to store this Krate's properties in.
     */
    public val sharedPreferences: SharedPreferences

}
