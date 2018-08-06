@file:Suppress("NOTHING_TO_INLINE")

package hu.autsoft.krate

import hu.autsoft.krate.default.BooleanDelegateWithDefault
import hu.autsoft.krate.default.FloatDelegateWithDefault
import hu.autsoft.krate.default.IntDelegateWithDefault
import hu.autsoft.krate.default.LongDelegateWithDefault
import hu.autsoft.krate.default.StringDelegateWithDefault
import hu.autsoft.krate.default.StringSetDelegateWithDefault
import hu.autsoft.krate.optional.BooleanDelegate
import hu.autsoft.krate.optional.FloatDelegate
import hu.autsoft.krate.optional.IntDelegate
import hu.autsoft.krate.optional.LongDelegate
import hu.autsoft.krate.optional.StringDelegate
import hu.autsoft.krate.optional.StringSetDelegate


inline fun Krate.booleanPref(key: String): BooleanDelegate {
    return BooleanDelegate(sharedPreferences, key)
}

inline fun Krate.floatPref(key: String): FloatDelegate {
    return FloatDelegate(sharedPreferences, key)
}

inline fun Krate.intPref(key: String): IntDelegate {
    return IntDelegate(sharedPreferences, key)
}

inline fun Krate.longPref(key: String): LongDelegate {
    return LongDelegate(sharedPreferences, key)
}

inline fun Krate.stringPref(key: String): StringDelegate {
    return StringDelegate(sharedPreferences, key)
}

inline fun Krate.stringSetPref(key: String): StringSetDelegate {
    return StringSetDelegate(sharedPreferences, key)
}


inline fun Krate.booleanPref(key: String, defaultValue: Boolean): BooleanDelegateWithDefault {
    return BooleanDelegateWithDefault(sharedPreferences, key, defaultValue)
}

inline fun Krate.floatPref(key: String, defaultValue: Float): FloatDelegateWithDefault {
    return FloatDelegateWithDefault(sharedPreferences, key, defaultValue)
}

inline fun Krate.intPref(key: String, defaultValue: Int): IntDelegateWithDefault {
    return IntDelegateWithDefault(sharedPreferences, key, defaultValue)
}

inline fun Krate.longPref(key: String, defaultValue: Long): LongDelegateWithDefault {
    return LongDelegateWithDefault(sharedPreferences, key, defaultValue)
}

inline fun Krate.stringPref(key: String, defaultValue: String): StringDelegateWithDefault {
    return StringDelegateWithDefault(sharedPreferences, key, defaultValue)
}

inline fun Krate.stringSetPref(key: String, defaultValue: Set<String>): StringSetDelegateWithDefault {
    return StringSetDelegateWithDefault(sharedPreferences, key, defaultValue)
}
