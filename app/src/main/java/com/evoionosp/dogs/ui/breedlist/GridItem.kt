package com.evoionosp.dogs.ui.breedlist

sealed class GridItem {
    data class Header(
        val breed: String,
        val name: String,
    ) : GridItem()

    data class Image(
        val url: String
    ) : GridItem()
}