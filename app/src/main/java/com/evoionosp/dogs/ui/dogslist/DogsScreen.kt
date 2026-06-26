package com.evoionosp.dogs.ui.dogslist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.evoionosp.dogs.ui.common.DogsAppBar
import com.evoionosp.dogs.ui.common.LoadingOverlay

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    onDogListItemClick: (String) -> Unit
) {
    val dogsListViewModel = hiltViewModel<DogsListViewModel>()

    val uiState = dogsListViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { DogsAppBar(title = "Dogs") }
    ) {paddingValues ->

        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            items(uiState.value.listItems) {
                DogListItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    breed = it,
                    onItemClick = {
                        onDogListItemClick(it.name)
                    }
                )
            }
        }

        LoadingOverlay(
            isLoading = uiState.value.isLoading,
            message = "Loading dogs..."
        )
    }

}