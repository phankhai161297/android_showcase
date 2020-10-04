package com.igorwojda.showcase.feature.activate.data.model

import com.igorwojda.showcase.feature.activate.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.activate.data.enum.toDomainEnum
import com.igorwojda.showcase.feature.activate.domain.model.AlbumImageDomainModel
import com.squareup.moshi.Json

internal data class AlbumImageDataModel(
    @field:Json(name = "#text") val url: String,
    val size: AlbumDataImageSize
)

internal fun AlbumImageDataModel.toDomainModel() = AlbumImageDomainModel(
    url = this.url,
    size = this.size.toDomainEnum()
)
