package com.evoionosp.dogs.data.remote

import com.evoionosp.dogs.data.model.BreedsResponse
import com.evoionosp.dogs.data.model.DogImageListResponse
import com.evoionosp.dogs.data.model.DogImageResponse
import com.evoionosp.dogs.data.model.SubBreedsResponse
import com.evoionosp.dogs.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl (val client: HttpClient) : ApiService {

    override suspend fun getDogs(): Response<BreedsResponse> {
        try {
            val response = client.get("$BASE_URL/breeds/list/all")
            val body = response.body<BreedsResponse>()
            return Response.Success(body)
        } catch (e : Exception) {
            return Response.Error(e.message ?: "Something went wrong")
        }

    }

    override suspend fun getDogsSubBreeds(breed: String): Response<SubBreedsResponse> {
        try {
            val response = client.get("$BASE_URL/breed/$breed/list")
            val body = response.body<SubBreedsResponse>()
            return Response.Success(body)
        } catch (e : Exception) {
            return Response.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getDogImage(breed: String): Response<DogImageResponse> {
        try {
            val response = client.get("$BASE_URL/breed/$breed/images/random")
            val body = response.body<DogImageResponse>()
            return Response.Success(body)
        } catch (e : Exception) {
            return Response.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getDogImageList(breed: String): Response<DogImageListResponse> {
        try {
            val response = client.get("$BASE_URL/breed/$breed/images")
            val body = response.body<DogImageListResponse>()
            return Response.Success(body)
        } catch (e : Exception) {
            return Response.Error(e.message ?: "Something went wrong")
        }
    }

    private companion object {
        const val BASE_URL = "https://dog.ceo/api"
    }

}