package com.evoionosp.dogs.data.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.evoionosp.dogs.domain.repo.RecentBreedsRepository
import com.evoionosp.dogs.util.Clock
import com.evoionosp.dogs.util.SystemClock
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.recentBreedsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "recent_breeds_prefs",
)

@Module
@InstallIn(SingletonComponent::class)
object RecentBreedsDataStoreModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): DataStore<Preferences> =
        context.recentBreedsDataStore
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RecentBreedsBindingsModule {
    @Binds
    @Singleton
    abstract fun bindClock(impl: SystemClock): Clock

    @Binds
    @Singleton
    abstract fun bindRecentBreedsRepository(impl: RecentBreedsRepositoryImpl): RecentBreedsRepository
}
