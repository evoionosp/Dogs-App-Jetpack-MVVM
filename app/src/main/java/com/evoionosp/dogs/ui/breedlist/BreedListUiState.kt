package com.evoionosp.dogs.ui.breedlist

import com.evoionosp.dogs.domain.model.Breed
import com.evoionosp.dogs.domain.model.RecentBreed
import com.evoionosp.dogs.domain.model.SubBreed

data class BreedListUiState (
    val listItems: List<GridItem> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val recentBreeds: List<RecentBreed> = emptyList(),
)
