package hu.autsoft.krate.gson.util

import android.content.SharedPreferences

internal inline fun SharedPreferences.edit(edits: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.edits()
    editor.apply()
}
