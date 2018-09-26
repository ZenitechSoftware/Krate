# Krate

_Krate_ is a `SharedPreferences` wrapper library that uses delegated properties for convenient access to `SharedPreferences` values.

Here's what its basic usage looks like, extending the `SimpleKrate` class:

```kotlin
class UserSettings(context: Context) : SimpleKrate(context) {

    var notificationsEnabled by booleanPref("notifications_enabled", false)
    var loginCount by intPref("login_count", 0)
    var nickname by stringPref("nickname", "")

}

val settings = UserSettings(context)
settings.loginCount = 10
Log.d("LOGIN_COUNT", "Count: ${settings.loginCount}")
```

# Dependency

You can include _Krate_ in your project from the `jcenter` repository, like so:

```groovy
implementation 'hu.autsoft:krate:0.0.3'
```

# Optionals vs defaults

Each property can be declared with or without a default value, here are the differences:

### Optional values:

A property declared with the one-argument delegate function will have a nullable type. It will have a `null` value if no value has been set for this property yet, and its current value can be erased from `SharedPreferences` completely by setting it to `null`.

```kotlin
var username: String? by stringPref("username")
```

### Default values:

A property declared with the two-argument delegate function takes its default value as the second argument, and it will have a non-nullable type. Reading from this property will return either the value it was last set to or the default value. Setting this property will update the value stored in `SharedPreferences`. Note that there's no way to remove these values from `SharedPreferences` (although you could set it explicitly to the default value).

```kotlin
var username: String by stringPref("username", defaultValue = "admin")
```

# Custom Krate implementations

You can usually get away with extending `SimpleKrate`, as it does allow you to pass in a custom name for the `SharedPreferences` to be used to store your values in its constructor as an optional parameter. (If you pass in no `name` parameter to its constructor, it will default to using the instance returned by `PreferenceManager.getDefaultSharedPreferences(context)`.)  

However, you can also implement the `Krate` interface directly if you want to manage the `SharedPreferences` instance yourself for whatever reason - all this interface requires is a property that holds a `SharedPreferences` instance. With that, you can use the delegate functions the same way as shown above: 

```kotlin
class ExampleCustomKrate(context: Context) : Krate {

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences("custom_krate_prefs", Context.MODE_PRIVATE)
    }

    var exampleBoolean by booleanPref("exampleBoolean", false)
    
}
```

# License

```
Copyright 2018 AutSoft

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
