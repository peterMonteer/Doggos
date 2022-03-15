package com.pedro.doggos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImagesUseCase
import com.pedro.doggos.feature_home.presentation.HomeViewModel
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import com.pedro.doggos.feature_search.presentation.SearchViewModel
import com.pedro.doggos.util.BreedFactory
import com.pedro.doggos.util.TestSchedulerProvider
import com.pedro.doggos.util.createHttpException
import com.pedro.doggos.util.randomString
import io.reactivex.Observable
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
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: BreedImagesRepository

    private lateinit var homeViewModel: HomeViewModel
    private var testScheduler = TestSchedulerProvider()

    private val breedImageExample = BreedImage(
        url = randomString(),
        id = randomString(),
        breed = BreedFactory.createBreedDto().toBreed()
    )

    @Before
    fun setUpViewModel(){
        homeViewModel = HomeViewModel(
            getBreedImagesUseCase = GetBreedImagesUseCase(repository),
            schedulerProvider = testScheduler
        )
    }

    @Test
    fun searchQueryAndViewModelStateChangeToSuccessState() {
        Mockito.`when`(repository
            .getBreedImages(any()))
            .thenReturn(Observable.just(PagingData.from(listOf(breedImageExample))))

        homeViewModel.getImages()
        testScheduler.testScheduler.triggerActions()
        assertTrue(homeViewModel.state.value is UIState.ErrorState)
    }
}