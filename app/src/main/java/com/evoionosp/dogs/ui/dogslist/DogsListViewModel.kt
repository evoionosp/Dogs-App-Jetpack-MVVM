package com.evoionosp.dogs.ui.dogslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoionosp.dogs.domain.repo.DogsRepository
import com.evoionosp.dogs.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class DogsListViewModel @Inject constructor(val repository: DogsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DogsListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeDogsList()
    }

    fun observeDogsList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDogsList().collect { response ->
                when(response){
                    is Response.Loading -> _uiState.value = uiState.value.copy(isLoading = true, error = null, listItems = emptyList())
                    is Response.Error -> _uiState.value = uiState.value.copy(error = response.message, isLoading = false)
                    is Response.Success -> _uiState.value = uiState.value.copy(listItems = response.data, isLoading = false, error = null)
                }
            }
        }

    }

}