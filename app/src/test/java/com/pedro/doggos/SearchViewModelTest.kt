package com.pedro.doggos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import com.pedro.doggos.feature_search.presentation.SearchViewModel
import com.pedro.doggos.util.TestSchedulerProvider
import com.pedro.doggos.util.createHttpException
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: BreedsRepository

    private lateinit var searchViewModel: SearchViewModel
    private var testScheduler = TestSchedulerProvider()

    private val httpException = createHttpException()

    private val breedExampleList = listOf(Breed(
        name = "Cocker",
        origin = "Portugal",
        temperament = "Such fun",
        group = "No groups"
    ))

    @Before
    fun setUpViewModel(){
        searchViewModel = SearchViewModel(
            getBreedsSearchUseCase = GetBreedsSearchUseCase(repository),
            schedulerProvider = testScheduler
        )
    }

    @Test
    fun searchQueryReturnFromRemoteSuccessViewModelStateChangeToSuccessState() {
        Mockito.`when`(repository
            .getBreedsSearchFromRemote(any()))
            .thenReturn(Single.just(breedExampleList))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()
        assertTrue(searchViewModel.state.value is SearchViewModel.State.SearchBreedsSuccess)
    }

    @Test
    fun offlineSearchQueryReturnSuccessViewModelStateChangeToSuccessState() {
        whenever(repository
            .getBreedsSearchFromRemote(any()))
            .thenReturn(Single.error(IOException()))

        Mockito.`when`(repository
            .getBreedsSearchFromLocalStorage(any()))
            .thenReturn(Single.just(breedExampleList))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()

        val state = searchViewModel.state.value as SearchViewModel.State.SearchBreedsSuccess
        assertEquals(breedExampleList, state.list)
    }

    @Test
    fun offlineSearchQueryReturnEmptyViewModelStateChangeToSuccessState() {
        whenever(repository
            .getBreedsSearchFromRemote(any()))
            .thenReturn(Single.error(IOException()))

        Mockito.`when`(repository
            .getBreedsSearchFromLocalStorage(any()))
            .thenReturn(Single.just(listOf()))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()

        val state = searchViewModel.state.value as SearchViewModel.State.SearchBreedsSuccess
        assertTrue(state.list.isEmpty())
    }

    @Test
    fun searchQueryReturnFromRemoteErrorAndViewModelStateChangesToErrorState() {
        Mockito.`when`(repository
            .getBreedsSearchFromRemote(any()))
            .thenReturn(Single.error(httpException))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()
        assertTrue(searchViewModel.state.value is UIState.ErrorState)
    }
}