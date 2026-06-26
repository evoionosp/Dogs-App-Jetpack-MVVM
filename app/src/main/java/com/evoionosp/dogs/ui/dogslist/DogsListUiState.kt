package com.evoionosp.dogs.ui.dogslist

import com.evoionosp.dogs.domain.model.Breed

data class DogsListUiState (
    val listItems: List<Breed> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)