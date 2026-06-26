package com.evoionosp.dogs.ui.data

import androidx.compose.ui.graphics.vector.ImageVector

data class ActionItem(val imageVector: ImageVector, val contentDescription: String, val onClick: () -> Unit)