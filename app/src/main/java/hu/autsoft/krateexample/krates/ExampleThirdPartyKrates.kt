package hu.autsoft.krateexample.krates

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.frybits.harmony.getHarmonySharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.stringPref

/**
 * Encrypted Krate based on the AndroidX EncryptedSharedPreferences class.
 */
class EncryptedKrate(applicationContext: Context) : Krate {
    override val sharedPreferences: SharedPreferences

    init {
        // Check the official docs on how to create EncryptedPreferences instances
        // https://developer.android.com/topic/security/data
        // https://developer.android.com/reference/kotlin/androidx/security/crypto/EncryptedSharedPreferences
        val masterKeyAlias = MasterKey.Builder(applicationContext)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        val sharedPrefsFile = "my_sensitive_data.txt"

        sharedPreferences = EncryptedSharedPreferences.create(
                applicationContext,
                sharedPrefsFile,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    val myStringValue: String by stringPref("my_string_value", "")
}

/**
 * Harmony is a multi-process SharedPreferences implementation.
 * See its GitHub page for details https://github.com/pablobaxter/Harmony
 */
class HarmonyKrate(context: Context) : Krate {
    override val sharedPreferences: SharedPreferences = context.getHarmonySharedPreferences("PREF_NAME")

    val myStringValue: String by stringPref("my_string_value", "")
}
