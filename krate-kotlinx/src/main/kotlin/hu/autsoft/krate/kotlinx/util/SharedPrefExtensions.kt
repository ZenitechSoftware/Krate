package hu.autsoft.krate.kotlinx.util

import android.content.SharedPreferences

internal inline fun SharedPreferences.edit(edits: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.edits()
    editor.apply()
}
