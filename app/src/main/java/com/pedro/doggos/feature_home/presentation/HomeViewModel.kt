package com.pedro.doggos.feature_home.presentation

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.pedro.doggos.core.presentation.BaseViewModel
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImages
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreedImages: GetBreedImages
) : BaseViewModel() {

    sealed class State: UIState {
        object GetImagesSuccess : State()
    }

    private val orderQueryList = listOf("ASC", "DESC", "RANDOM")
    private var previousOrderQuery = ""

    var breedImageList: MutableLiveData<PagingData<BreedImage>> = MutableLiveData()

    init {
        getImages()
    }

    fun getImages() {
        val query = if (previousOrderQuery.isEmpty()) {
            orderQueryList[0]
        } else {
            val position = orderQueryList.indexOf(previousOrderQuery)
            val newQueryPosition = if (position + 1 > 2) 0 else position + 1
            orderQueryList[newQueryPosition]
        }
        getBreedImages(query)
            .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)

        previousOrderQuery = query
    }

    private fun onSuccess(list: PagingData<BreedImage>){
        breedImageList.postValue(list)
        state.value = State.GetImagesSuccess
    }

    private fun onError(throwable: Throwable) {
        Log.d("Error", "Error")
    }
}