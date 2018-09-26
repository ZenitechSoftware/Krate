package hu.autsoft.krate.util

import android.content.SharedPreferences

internal fun SharedPreferences.edit(edits: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.edits()
    editor.apply()
}
