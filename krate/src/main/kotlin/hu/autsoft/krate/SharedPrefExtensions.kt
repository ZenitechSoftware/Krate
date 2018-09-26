package hu.autsoft.krate

import android.content.SharedPreferences

fun SharedPreferences.edit(edits: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.edits()
    editor.apply()
}
