package hu.autsoft.krate.moshi.util

import android.content.SharedPreferences

internal inline fun SharedPreferences.edit(edits: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.edits()
    editor.apply()
}
