# Dogs - Android MVVM + Clean Architecture Tutorial

A modern Android application demonstrating **Clean Architecture**, **MVVM**, **Jetpack Compose**, and the latest **Navigation 3** implementation. This project serves as a tutorial for building scalable and maintainable Android apps using the latest industry standards.

## 🚀 Overview

The **Dogs** app allows users to browse a list of dog breeds, explore sub-breeds, and view beautiful images of their favorite furry friends. It fetches data from the [Dog API](https://dog.ceo/dog-api/).

## 🏗️ Architecture

The project follows **Clean Architecture** principles, separating the code into three main layers:

### 1. Domain Layer
*   **Entities:** Pure Kotlin data classes (`Breed`, `SubBreed`).
*   **Repositories (Interfaces):** Defines the contracts for data operations.
*   **Use Cases (Optional):** Business logic (can be added for more complex scenarios).

### 2. Data Layer
*   **Repositories (Implementation):** Implements the domain interfaces, managing data from network sources.
*   **Remote (Network):** Ktor client implementation for API calls.
*   **Models:** API response models using Kotlin Serialization.

### 3. UI Layer (Presentation)
*   **MVVM:** ViewModels manage UI state using `StateFlow`.
*   **Jetpack Compose:** Declarative UI components.
*   **Navigation 3:** The latest iteration of Compose Navigation, offering a state-based approach to navigation.

## 🛠️ Tech Stack

*   **Language:** Kotlin
*   **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Navigation:** [Navigation 3](https://developer.android.com/guide/navigation/navigation-3)
*   **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
*   **Networking:** [Ktor Client](https://ktor.io/docs/client-dependencies.html)
*   **Image Loading:** [Coil 3](https://coil-kt.github.io/coil/)
*   **Asynchronous Programming:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
*   **Serialization:** [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)

## 📂 Project Structure

```text
com.evoionosp.dogs
├── data                # Data layer implementation
│   ├── model           # API models
│   ├── remote          # Ktor API service
│   └── repo            # Repository implementations
├── domain              # Domain layer (Business logic)
│   ├── model           # Domain entities
│   └── repo            # Repository interfaces
├── ui                  # UI layer
│   ├── breedlist       # Sub-breed list feature
│   ├── dogslist        # Main breeds list feature
│   ├── navigation      # Navigation 3 setup (Route, AppNavigation)
│   ├── common          # Reusable UI components (DogsAppBar)
│   └── theme           # Material 3 theme setup
└── utils               # Utility classes (Response wrapper)
```

## 🗺️ Navigation 3 Implementation

This project highlights the use of **Navigation 3**, which simplifies navigation by treating it as a state. Key components include:

*   `Route`: A sealed interface defining the application's destinations using `@Serializable`.
*   `rememberNavBackStack`: Manages the navigation stack.
*   `NavDisplay`: Handles the rendering of screens based on the current backstack state.

## 🏁 Getting Started

1.  **Clone the repository.**
2.  **Open in Android Studio** (Ladybug or newer recommended).
3.  **Sync Gradle** to download dependencies.
4.  **Run the app** on an emulator or physical device.

## 🙏 Acknowledgements

This project uses the [Dog API](https://dog.ceo/dog-api/) for fetching breed data and images. Special thanks to the maintainers for providing this free and open API.

## 📝 License

```text
Copyright 2025 Shubh

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
