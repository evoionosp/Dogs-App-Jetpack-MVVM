package com.evoionosp.dogs.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.evoionosp.dogs.ui.data.ActionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsAppBar(
    actionItems: List<ActionItem> = emptyList(),
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {Text(text = title)},
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = onNavigationClick
                ) {
                    Icon(imageVector = navigationIcon, contentDescription = "navigation button")
                }
            }
        },
        actions = {
            actionItems.forEach {
                IconButton(onClick = it.onClick) {
                    Icon(imageVector = it.imageVector, contentDescription = it.contentDescription)
                }
            }
        }
    )
}


