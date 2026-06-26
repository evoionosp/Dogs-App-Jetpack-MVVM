package com.evoionosp.dogs.ui.breedlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.evoionosp.dogs.ui.common.DogsAppBar
import com.evoionosp.dogs.ui.common.LoadingOverlay

@Composable
fun SubBreedListScreen(
    breed: String
) {
    val breedListViewModel = hiltViewModel<BreedListViewModel>()

    val uiState = breedListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(breed) {
        breedListViewModel.observeSubBreedList(breed)
    }

    Scaffold(
    ) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = uiState.value.listItems,
                key = { item ->
                    when (item) {
                        is GridItem.Header -> item.breed + item.name
                        is GridItem.Image -> item.url
                    }
                },
                span = { item ->
                    if (item is GridItem.Header) GridItemSpan(maxLineSpan)
                    else GridItemSpan(1)
                }
            ) { item ->
                when (item) {
                    is GridItem.Header -> {
                        Column {
                            Text(
                                modifier = Modifier.padding(start = 4.dp, bottom = 8.dp, top = 36.dp),
                                text = item.breed.toUpperCase(Locale.current),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )

                            if (item.name.isNotBlank()) {
                                Text(
                                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
                                    text = item.name,
                                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Light)
                                )
                            }
                        }
                    }

                    is GridItem.Image -> {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.url)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Dog image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        LoadingOverlay(
            isLoading = uiState.value.isLoading,
            message = "Loading breeds..."
        )
    }



}