package com.igorwojda.showcase.feature.activate.data.repository

import com.igorwojda.showcase.feature.activate.data.DataFixtures
import com.igorwojda.showcase.feature.activate.data.model.AlbumListDataModel
import com.igorwojda.showcase.feature.activate.data.model.AlbumSearchResultDataModel
import com.igorwojda.showcase.feature.activate.data.model.toDomainModel
import com.igorwojda.showcase.feature.activate.data.retrofit.reponse.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.activate.data.retrofit.reponse.SearchAlbumResponse
import com.igorwojda.showcase.feature.activate.data.retrofit.service.AlbumRetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class AlbumRepositoryImplTest {

    @MockK
    internal lateinit var mockService: AlbumRetrofitService

    private lateinit var cut: AlbumRepositoryImpl

    private val artistName = "artistName"
    private val albumName = "albumName"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = AlbumRepositoryImpl(mockService)
    }

    @Test
    fun `getAlbumInfo fetches AlbumInfo and maps to Model`() {
        // given
        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns GetAlbumInfoResponse(DataFixtures.getAlbum().copy())

        // when
        val result = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        result shouldBeEqualTo DataFixtures.getAlbum().toDomainModel()
    }

    @Test
    fun `getAlbumInfo returns null if response is null`() {
        // given
        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns null

        // when
        val result = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        result shouldBeEqualTo null
    }

    @Test
    fun `searchAlbum fetches AlbumInfo and maps to Model`() {
        // given
        val phrase = "phrase"
        coEvery { mockService.searchAlbumAsync(phrase) } returns SearchAlbumResponse(
            AlbumSearchResultDataModel(
                AlbumListDataModel(listOf(DataFixtures.getAlbum()))
            )
        )

        // when
        val result = runBlocking { cut.searchAlbum(phrase) }

        // then
        result shouldBeEqualTo listOf(DataFixtures.getAlbum().toDomainModel())
    }
}