package com.evoionosp.dogs.data.repo

import com.evoionosp.dogs.data.remote.ApiService
import com.evoionosp.dogs.domain.model.Breed
import com.evoionosp.dogs.domain.model.SubBreed
import com.evoionosp.dogs.domain.repo.DogsRepository
import com.evoionosp.dogs.utils.Response
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(private val apiService: ApiService) : DogsRepository {
    override suspend fun getDogsList(): Flow<Response<List<Breed>>> {
        return flow {
            emit(Response.Loading)
            when(val response = apiService.getDogs()){
                is Response.Error -> emit(Response.Error("Something went wrong"))
                is Response.Loading -> emit(Response.Loading)
                is Response.Success -> {
                    if(response.data.status == SUCCESS){
                        val dogsList = response.data.message.map {
                            Breed(it.key, it.value, "")
                        }
                        emit(Response.Success(dogsList))

                        //Load images
                        coroutineScope {
                            val dogListWithImages = dogsList.map { breed ->
                                async {
                                    when(val dogImageResponse = apiService.getDogImage(breed.name)){
                                        is Response.Success -> {
                                            if(response.data.status == SUCCESS){
                                                breed.copy(imageUrl = dogImageResponse.data.message)
                                            } else breed
                                        }
                                        else -> breed
                                    }
                                }
                            }
                            emit(Response.Success(dogListWithImages.awaitAll()))
                        }

                    } else {
                        emit(Response.Error("No dogs found !!"))
                    }
                }
            }
        }

    }

    override suspend fun getSubBreeds(breed: String): Flow<Response<List<SubBreed>>> {
        return flow {
            emit(Response.Loading)

            when(val response = apiService.getDogsSubBreeds(breed)){
                is Response.Loading -> emit(Response.Loading)
                is Response.Error -> emit(Response.Error(response.message))
                is Response.Success -> {
                    if(response.data.status == SUCCESS) {
                        var subBreeds = response.data.message.map {
                            SubBreed(name = it, breed = breed, imageUrls = emptyList())
                        }

                        if(subBreeds.isEmpty()) {
                            subBreeds = listOf(SubBreed(name = "", breed = breed, imageUrls = emptyList()))
                        }
                        emit(Response.Success(subBreeds))

                        coroutineScope {

                            val subBreedsWithImages = subBreeds.map { subBreed ->
                                val breedUrl = if(subBreed.name.isBlank()) subBreed.breed else "${subBreed.breed}/${subBreed.name}"
                                async {
                                    when (val dogImageResponse =
                                        apiService.getDogImageList(breedUrl)) {
                                        is Response.Success -> {
                                            if (response.data.status == SUCCESS) {
                                                subBreed.copy(imageUrls = dogImageResponse.data.message)
                                            } else subBreed
                                        }

                                        else -> subBreed
                                    }
                                }
                            }

                            emit(Response.Success(subBreedsWithImages.awaitAll()))
                        }

                    } else {
                        emit(Response.Error("No sub breeds found !!"))
                    }
                }
            }
        }
    }

    companion object {
        const val SUCCESS = "success"
    }
}