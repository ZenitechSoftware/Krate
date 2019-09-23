# Krate

![Krate banner](./docs/krate.png)

_Krate_ is a `SharedPreferences` wrapper library that uses delegated properties for convenient access to `SharedPreferences` values.

Here's what its basic usage looks like, extending the provided `SimpleKrate` class:

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

You can include _Krate_ in your project from the `mavenCentral()` repository, like so:

```groovy
implementation 'hu.autsoft:krate:0.3.0'
```

# Optionals vs defaults

Each stored property can be declared with or without a default value. Here's how the two options differ:

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

For simple applications, your `Activity` or `Fragment` can easily serve as a `Krate` implementation:

```kotlin
class MainActivity : AppCompatActivity(), Krate {

    override val sharedPreferences: SharedPreferences by lazy {
        getPreferences(Context.MODE_PRIVATE) // Could also fetch a named or default SharedPrefs
    }
    
    var username by stringPref("username", "")

}
```

# Validation

You can add validation rules to your Krate properties by providing an additional lambda parameter, `isValid`:

```kotlin
var percentage: Int by intPref(
        key = "percentage",
        defaultValue = 0,
        isValid = { it in 0..100 }
)
```

If this validation fails, an `IllegalArgumentException` will be thrown.

# Addons

Krate, by default, supports the types that `SharedPreferences` supports. These are `Boolean`, `Float`, `Int`, `Long`, `String` and `Set<String>`. You may of course want to store additional types in Krate. For some common types, addon libraries are available, as described below.

If you don't find support for the type you're looking for, implementing your own delegate in your own project based on the code of existing delegates should be quite simple, this is very much a supported use case. If you think your type might be commonly used, you can also open an issue to ask for an addon library to provide for that type.

### krate-gson

`krate-gson` provides you a `gsonPref` delegate which can store any arbitrary type, as long as GSON can serialize and deserialize it. This addon, like the base library, is available from `mavenCentral()`:

```groovy
implementation 'hu.autsoft:krate-gson:0.3.0'
```

Its basic usage is the same as with any of the base library's delegates:

```kotlin
class GsonKrate(context: Context) : SimpleKrate(context) {
    var user: User? by gsonPref("user")
    var savedArticles: List<Article>? by gsonPref("articles")
}
```

By default, the `Gson` instance created by a simple `Gson()` constructor call is used. If you want to provide your own `Gson` instance that you've configured, you can set the `gson` extension property on your Krate. Any `gsonPref` delegates within this Krate will use this instance for serialization and deserialization.

```kotlin
class CustomGsonKrate(context: Context) : SimpleKrate(context) {
    init {
        gson = GsonBuilder().create()
    }
}
``` 

# License

```
Copyright 2019 AutSoft

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
