package hu.autsoft.krate.moshi

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.default.withDefault
import hu.autsoft.krate.validation.validate


internal class MoshiTestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    companion object {
        val DEFAULT_SIMPLE_VALUE = TestModel(x = 1, y = 2.0, z = "3")
        val DEFAULT_LIST_VALUE = emptyList<TestModel>()
    }

    var simpleValue: TestModel? by moshiPref("simpleValue")

    var listOfValues: List<TestModel>? by moshiPref("listOfValues")

    var simpleValueWithDefault
        by moshiPref<TestModel>("simpleValueWithDefault")
            .withDefault(DEFAULT_SIMPLE_VALUE)

    var listOfValuesWithDefault
        by moshiPref<List<TestModel>>("listOfValuesWithDefault")
            .withDefault(DEFAULT_LIST_VALUE)

    var validatedValue
        by moshiPref<TestModel>("validatedValue")
            .withDefault(DEFAULT_SIMPLE_VALUE)
            .validate { newValue ->
                newValue.x < newValue.y // arbitrary rule
            }

    var validatedOptionalValue
        by moshiPref<List<TestModel>>(key = "validatedOptionalValue")
            .validate { newValue ->
                newValue.isNullOrEmpty().not() // arbitrary rule
            }

}
