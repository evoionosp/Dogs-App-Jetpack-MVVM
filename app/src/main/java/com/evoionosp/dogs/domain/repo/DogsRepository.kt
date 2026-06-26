package com.evoionosp.dogs.domain.repo

import com.evoionosp.dogs.domain.model.Breed
import com.evoionosp.dogs.domain.model.SubBreed
import com.evoionosp.dogs.utils.Response
import kotlinx.coroutines.flow.Flow

interface DogsRepository {

    suspend fun getDogsList(): Flow<Response<List<Breed>>>

    suspend fun getSubBreeds(breed: String): Flow<Response<List<SubBreed>>>
}