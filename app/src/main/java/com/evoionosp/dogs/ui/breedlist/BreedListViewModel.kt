package com.evoionosp.dogs.ui.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoionosp.dogs.domain.repo.DogsRepository
import com.evoionosp.dogs.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(val repository: DogsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(BreedListUiState())
    val uiState = _uiState.asStateFlow()


    fun observeSubBreedList(breed: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSubBreeds(breed).collect { response ->
                when(response){
                    is Response.Loading -> _uiState.value = uiState.value.copy(isLoading = true, error = null, listItems = emptyList())
                    is Response.Error -> _uiState.value = uiState.value.copy(error = response.message, isLoading = false)
                    is Response.Success -> {
                        val items = response.data.flatMap { subBreed ->
                            listOf(
                                GridItem.Header(subBreed.breed, subBreed.name)
                            ) + subBreed.imageUrls.map {
                                GridItem.Image(it)
                            }
                        }
                        _uiState.value = uiState.value.copy(listItems = items, isLoading = false, error = null)
                    }
                }
            }
        }

    }

}