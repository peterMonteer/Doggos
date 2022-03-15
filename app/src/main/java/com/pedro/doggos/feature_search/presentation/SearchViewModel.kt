package com.pedro.doggos.feature_search.presentation

import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.core.presentation.BaseViewModel
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.core.util.SchedulerProvider
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getBreedsSearchUseCase: GetBreedsSearchUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    sealed class State: UIState {
        class SearchBreedsSuccess(val list: List<Breed>) : State()
    }

    fun searchBreeds(searchQuery: String) {
        getBreedsSearchUseCase(searchQuery)
            .subscribeOn(schedulerProvider.ioScheduler)
            .observeOn(schedulerProvider.uiScheduler)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(list: List<Breed>){
        if (list.isEmpty()) {
            state.value = UIState.ErrorState("Your search returned 0 results")
        } else {
            state.value = State.SearchBreedsSuccess(list)
        }
    }

    private fun onError(throwable: Throwable) {
        val message = if (throwable is IOException) "A problem occurred please check your internet connection" else "An error occurred"
        state.value = UIState.ErrorState(message)
    }
}