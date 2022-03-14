package com.pedro.doggos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
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

    private val breedExample = Breed(
        name = "Cocker",
        origin = "Portugal",
        temperament = "Such fun",
        group = "No groups"
    )

    @Before
    fun setUpViewModel(){
        searchViewModel = SearchViewModel(
            getBreedsSearchUseCase = GetBreedsSearchUseCase(repository),
            schedulerProvider = testScheduler
        )
    }

    @Test
    fun searchQueryAndViewModelStateChangeToSuccessState() {
        Mockito.`when`(repository
            .getBreedsSearch(any()))
            .thenReturn(Single.just(listOf(breedExample)))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()
        assertTrue(searchViewModel.state.value is SearchViewModel.State.SearchBreedsSuccess)
    }

    @Test
    fun searchErrorAndViewModelStateChangesToErrorState() {
        val httpException = createHttpException()

        Mockito.`when`(repository
            .getBreedsSearch(any()))
            .thenReturn(Single.error(httpException))

        searchViewModel.searchBreeds("something")
        testScheduler.testScheduler.triggerActions()
        assertTrue(searchViewModel.state.value is UIState.ErrorState)
    }
}