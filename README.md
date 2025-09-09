# 🍴 Recipe App with Custom KList DSL

This project demonstrates how to build a **custom Kotlin DSL** for handling complex lists in Jetpack Compose.  
We designed a class called **`KList`** that behaves like a builder for lists, allowing developers to write clean, expressive, and reusable UI code.

<img width="2000" height="1400" alt="image" src="https://github.com/user-attachments/assets/1e982857-dbbc-43b2-8a85-956bdb78dfa0" />

---

## 🚀 Features
- **Kotlin DSL** based list builder (`KList`).
- Supports **single section** as well as **multiple sections**.
- Built-in support for:
  - Padding
  - Dividers
  - Click handling
  - Empty state UI
  - Smooth staggered animations (items appear one by one).
- Section-wise Recipe example (**Lunch, Dinner, Dessert, Drinks**).
- Uses **Material 3** components.
- Ready to extend (e.g., adding images inside items).

---

## 🛠️ What is Kotlin DSL?

DSL (Domain Specific Language) in Kotlin allows us to design APIs that look like natural language.  
Instead of boilerplate list setup, we describe what we want in a **fluent style**.

👉 Example of DSL style:

```kotlin
KList.instance
    .padding(16.dp)
    .divider()
    .animateItems()
    .section("🍱 Lunch Specials", lunchRecipes) { RecipeItem(it) }
    .section("🍲 Dinner Delights", dinnerRecipes) { RecipeItem(it) }
    .section("🍰 Desserts", dessertRecipes) { RecipeItem(it) }
    .section("🥤 Drinks", drinksRecipes) { RecipeItem(it) }
    .emptyState { Text("No recipes found 🍳") }
    .render()


#This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
