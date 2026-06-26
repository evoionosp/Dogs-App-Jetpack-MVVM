package com.evoionosp.dogs.ui.dogslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.evoionosp.dogs.R
import com.evoionosp.dogs.domain.model.Breed

@Composable
fun DogListItem(
    modifier: Modifier,
    breed: Breed,
    onItemClick: () -> Unit,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier.size(75.dp).clip(CircleShape),
                model = breed.imageUrl,
                contentDescription = "Image of ${breed.name}",
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxWidth().weight(1.0f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Text(text = breed.name.toUpperCase(Locale.current), style = MaterialTheme.typography.headlineSmall)

                Text(text = "${breed.subBreeds.count()} Sub breed", style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)))
            }
        }
    }

}