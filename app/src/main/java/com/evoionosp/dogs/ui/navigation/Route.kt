package com.evoionosp.dogs.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    object DogsList : Route

    @Serializable
    data class SubBreedList(val breed: String) : Route
}