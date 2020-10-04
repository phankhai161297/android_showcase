package com.igorwojda.showcase.feature.activate.data.repository

import com.igorwojda.showcase.feature.activate.data.model.toDomainModel
import com.igorwojda.showcase.feature.activate.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.activate.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService
) : AlbumRepository {

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?) =
        albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
            ?.album
            ?.toDomainModel()

    override suspend fun searchAlbum(phrase: String) =
        albumRetrofitService.searchAlbumAsync(phrase)
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}
