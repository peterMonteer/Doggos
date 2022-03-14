package com.pedro.doggos.feature_search.presentation

import android.os.AsyncTask
import android.util.Log
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.core.presentation.BaseViewModel
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getBreedsSearchUseCase: GetBreedsSearchUseCase
) : BaseViewModel() {

    sealed class State: UIState {
        class SearchBreedsSuccess(val list: List<Breed>) : State()
    }

    fun searchBreeds(searchQuery: String) {
        getBreedsSearchUseCase(searchQuery)
            .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(list: List<Breed>){
        state.value = State.SearchBreedsSuccess(list)
    }

    private fun onError(throwable: Throwable) {
        Log.d("Error", "Error")
    }
}