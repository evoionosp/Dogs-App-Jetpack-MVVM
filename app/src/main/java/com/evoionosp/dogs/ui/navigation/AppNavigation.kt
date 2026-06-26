package com.evoionosp.dogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.evoionosp.dogs.ui.breedlist.SubBreedListScreen
import com.evoionosp.dogs.ui.dogslist.DogsScreen

@Composable
fun AppNavigation() {

    val backStack = rememberNavBackStack(Route.DogsList)

    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = {key ->
            when(key) {
                is Route.DogsList -> NavEntry(
                    key = Route.DogsList,
                    content = {
                        DogsScreen(
                            onDogListItemClick = {
                                breed -> backStack.add(Route.SubBreedList(breed))
                            }
                        )
                    }
                )
                is Route.SubBreedList -> NavEntry(
                    key = Route.SubBreedList(key.breed),
                    content = {
                        SubBreedListScreen(
                            breed = key.breed
                        )
                    }
                )
                else -> throw IllegalArgumentException("Unknown route $key")
            }
        }
    )
}