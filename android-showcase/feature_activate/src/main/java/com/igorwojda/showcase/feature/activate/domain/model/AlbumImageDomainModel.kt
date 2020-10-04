package com.igorwojda.showcase.feature.activate.domain.model

import com.igorwojda.showcase.feature.activate.domain.enum.AlbumDomainImageSize

internal data class AlbumImageDomainModel(
    val url: String,
    val size: AlbumDomainImageSize
)
