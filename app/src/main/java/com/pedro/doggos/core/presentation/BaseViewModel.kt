package com.pedro.doggos.core.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var state: MutableLiveData<UIState> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}