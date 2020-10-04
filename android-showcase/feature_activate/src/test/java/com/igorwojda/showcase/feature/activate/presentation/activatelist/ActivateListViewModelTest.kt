package com.igorwojda.showcase.feature.activate.presentation.activatelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorwojda.showcase.feature.activate.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.activate.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.activate.presentation.activatelist.ActivateListViewModel.ViewState
import com.igorwojda.showcase.library.base.presentation.navigation.NavManager
import com.igorwojda.showcase.library.testutils.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ActivateListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var mockGetAlbumListUseCase: GetAlbumListUseCase

    @MockK(relaxed = true)
    internal lateinit var mockNavManager: NavManager

    private lateinit var cut: ActivateListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = ActivateListViewModel(
            mockNavManager,
            mockGetAlbumListUseCase
        )
    }

    @Test
    fun `execute getAlbumUseCase`() {
        // when
        cut.loadData()

        // then
        coVerify { mockGetAlbumListUseCase.execute() }
    }

    @Test
    fun `navigate to album details`() {
        // given
        val artistName = "artistName"
        val albumName = "albumName"
        val mbId = "mbId"
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns empty list`() {
        // given
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(emptyList())

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo ViewState(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns non-empty list`() {
        // given
        val album = AlbumDomainModel("albumName", "artistName", listOf())
        val albums = listOf(album)
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(albums)

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo ViewState(
            isLoading = false,
            isError = false,
            albums = albums
        )
    }
}
