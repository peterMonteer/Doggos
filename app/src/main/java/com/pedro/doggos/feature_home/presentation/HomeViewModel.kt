package com.pedro.doggos.feature_home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.pedro.doggos.core.presentation.BaseViewModel
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.core.util.SchedulerProvider
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreedImagesUseCase: GetBreedImagesUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val orderQueryList = listOf("ASC", "DESC", "RANDOM")
    private var previousOrderQuery = ""

    var breedImageList: MutableLiveData<PagingData<BreedImage>> = MutableLiveData()

    fun getImages() {

        // Cycle through the different options for the order query parameter
        val query = if (previousOrderQuery.isEmpty()) {
            orderQueryList[0]
        } else {
            val position = orderQueryList.indexOf(previousOrderQuery)
            val newQueryPosition = if (position + 1 > 2) 0 else position + 1
            orderQueryList[newQueryPosition]
        }

        getBreedImagesUseCase(query, viewModelScope)
            .subscribeOn(schedulerProvider.ioScheduler)
            .observeOn(schedulerProvider.uiScheduler)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)

        previousOrderQuery = query
    }

    private fun onSuccess(list: PagingData<BreedImage>){
        breedImageList.postValue(list)
    }

    private fun onError(throwable: Throwable) {
        val message = if (throwable is IOException) "A problem occurred please check your internet connection" else "An error occurred"
        state.value = UIState.ErrorState(message)
    }
}