package com.evoionosp.dogs.data.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.evoionosp.dogs.domain.model.RecentBreed
import com.evoionosp.dogs.domain.repo.RecentBreedsRepository
import com.evoionosp.dogs.util.Clock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentBreedsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val clock: Clock,
) : RecentBreedsRepository {

    private val _internal = MutableStateFlow<List<RecentBreed>>(emptyList())
    override val recentBreeds: StateFlow<List<RecentBreed>> = _internal

    override suspend fun recordView(breedName: String) {
        TODO()
    }
}
