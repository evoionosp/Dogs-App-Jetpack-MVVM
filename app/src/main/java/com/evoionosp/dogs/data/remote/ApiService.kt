package com.evoionosp.dogs.data.remote

import com.evoionosp.dogs.data.model.BreedsResponse
import com.evoionosp.dogs.data.model.DogImageListResponse
import com.evoionosp.dogs.data.model.DogImageResponse
import com.evoionosp.dogs.data.model.SubBreedsResponse
import com.evoionosp.dogs.utils.Response

interface ApiService {
    suspend fun getDogs(): Response<BreedsResponse>

    suspend fun getDogsSubBreeds(breed: String): Response<SubBreedsResponse>

    suspend fun getDogImage(breed: String): Response<DogImageResponse>

    suspend fun getDogImageList(breed: String): Response<DogImageListResponse>
}
