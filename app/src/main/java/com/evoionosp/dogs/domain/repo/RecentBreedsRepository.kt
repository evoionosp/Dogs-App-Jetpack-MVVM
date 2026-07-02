package com.evoionosp.dogs.domain.repo

import com.evoionosp.dogs.domain.model.RecentBreed
import kotlinx.coroutines.flow.StateFlow

interface RecentBreedsRepository {
    val recentBreeds: StateFlow<List<RecentBreed>>
    suspend fun recordView(breedName: String)
}
