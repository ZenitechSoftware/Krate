package hu.autsoft.krateexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hu.autsoft.krateexample.krates.ExampleCustomKrate
import hu.autsoft.krateexample.krates.ExampleSettings
import hu.autsoft.krateexample.krates.ExampleSimpleKrate
import kotlinx.android.synthetic.main.activity_example.*

class ExampleActivity : AppCompatActivity() {

    companion object {
        const val KEY_KRATE_TYPE = "KEY_KRATE_TYPE"
        const val TYPE_SIMPLE = "TYPE_SIMPLE"
        const val TYPE_CUSTOM = "TYPE_CUSTOM"
    }

    lateinit var exampleSettings: ExampleSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        when (intent.getStringExtra(KEY_KRATE_TYPE)) {
            TYPE_SIMPLE -> {
                exampleSettings = ExampleSimpleKrate(this)
                title = "Simple Krate"
            }
            TYPE_CUSTOM -> {
                exampleSettings = ExampleCustomKrate(this)
                title = "Custom Krate"
            }
            else -> throw IllegalArgumentException("Invalid Krate type in Intent")
        }
    }

    override fun onResume() {
        super.onResume()

        booleanPreference.isChecked = exampleSettings.exampleBoolean
        floatPreferenceInput.setText(exampleSettings.exampleFloat.toString())
        intPreferenceInput.setText(exampleSettings.exampleInt.toString())
        longPreferenceInput.setText(exampleSettings.exampleLong.toString())
        stringPreferenceInput.setText(exampleSettings.exampleString)
        stringSetPreferenceInput.setText(exampleSettings.exampleStringSet.joinToString(separator = ", "))
    }

    override fun onPause() {
        super.onPause()

        exampleSettings.exampleBoolean = booleanPreference.isChecked
        exampleSettings.exampleFloat = floatPreferenceInput.text.toString().toFloat()
        exampleSettings.exampleInt = intPreferenceInput.text.toString().toInt()
        exampleSettings.exampleLong = longPreferenceInput.text.toString().toLong()
        exampleSettings.exampleString = stringPreferenceInput.text.toString()
        exampleSettings.exampleStringSet = stringSetPreferenceInput.text.toString().split(",").map(String::trim).toSet()
    }

}
