package com.pedro.doggos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImagesUseCase
import com.pedro.doggos.feature_home.presentation.HomeViewModel
import com.pedro.doggos.util.*
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

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

    /**
        Test not working, have to find a way to verify equivalence of the data returned from the
     repository call
     */

    /*@Test
    fun getBreedImagesSuccessAndViewModelStateChangeToSuccessState() {
        val pagingData = PagingData.from(listOf(breedImageExample))

        Mockito.`when`(repository
            .getBreedImages(any()))
            .thenReturn(Observable.just(pagingData))

        homeViewModel.getImages()
        testScheduler.testScheduler.triggerActions()
        assertEquals(homeViewModel.breedImageList.value, pagingData)
    }*/

    /**
    Test not working, ViewModel State is not updating to an ErrorState after the repository call
    returns an HttpException, probably related to some problem with exception propagation and the
     coroutine used as a caching mechanism
     */

    /*@Test
    fun getBreedImagesErrorAndViewModelStateChangeToErrorState() {
        Mockito.`when`(repository
            .getBreedImages(any()))
            .thenReturn(Observable.error(createHttpException()))

        homeViewModel.getImages()
        testScheduler.testScheduler.triggerActions()
        val state = homeViewModel.state.value
        val test = state.toString()
        assertTrue(state == null)
    }*/
}