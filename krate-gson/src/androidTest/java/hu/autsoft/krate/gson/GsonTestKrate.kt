package hu.autsoft.krate.gson

import android.content.Context
import hu.autsoft.krate.SimpleKrate


class GsonTestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    companion object {
        val DEFAULT_SIMPLE_VALUE = TestModel(x = 1, y = 2.0, z = "3")
        val DEFAULT_LIST_VALUE = emptyList<TestModel>()
    }

    var simpleValue: TestModel? by gsonPref("simpleValue")

    var listOfValues: List<TestModel>? by gsonPref("listOfValues")

    var simpleValueWithDefault: TestModel
            by gsonPref("simpleValueWithDefault", DEFAULT_SIMPLE_VALUE)

    var listOfValuesWithDefault: List<TestModel>
            by gsonPref("listOfValuesWithDefault", defaultValue = DEFAULT_LIST_VALUE)

    var validatedValue: TestModel by gsonPref(
            key = "validatedValue",
            defaultValue = DEFAULT_SIMPLE_VALUE,
            isValid = { newValue ->
                newValue.x < newValue.y // arbitrary rule
            }
    )

    var validatedOptionalValue: List<TestModel>? by gsonPref(
            key = "validatedOptionalValue",
            isValid = { newValue ->
                newValue.isNullOrEmpty().not()
            }
    )

}
