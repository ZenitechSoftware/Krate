package hu.autsoft.krate.kotlinx

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.validation.validate


internal class KotlinxTestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    companion object {
        val DEFAULT_SIMPLE_VALUE = TestModel(x = 1, y = 2.0, z = "3")
        val DEFAULT_LIST_VALUE = emptyList<TestModel>()
    }

    var simpleValue: TestModel? by kotlinxPref("simpleValue")

    var listOfValues: List<TestModel>? by kotlinxPref("listOfValues")

    var simpleValueWithDefault: TestModel
        by kotlinxPref("simpleValueWithDefault").withDefault(DEFAULT_SIMPLE_VALUE)

    var listOfValuesWithDefault: List<TestModel>
            by kotlinxPref("listOfValuesWithDefault").withDefault(DEFAULT_LIST_VALUE)

    var validatedValue: TestModel
            by kotlinxPref("validatedValue").withDefault(DEFAULT_SIMPLE_VALUE)
                    .validate { newValue ->
                        newValue.x < newValue.y // arbitrary rule
                    }

    var validatedOptionalValue: List<TestModel>?
            by kotlinxPref<List<TestModel>>(key = "validatedOptionalValue")
                    .validate { newValue ->
                        newValue.isNullOrEmpty().not() // arbitrary rule
                    }

}
