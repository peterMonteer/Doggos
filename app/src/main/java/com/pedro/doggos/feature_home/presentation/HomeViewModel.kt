package com.pedro.doggos.feature_home.presentation

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImages
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreedImages: GetBreedImages
) : ViewModel() {

    var compositeDisposable = CompositeDisposable()

    init {
        getBreedImages()
            .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun onSuccess(list: List<BreedImage>){
        Log.d("Success", "Great")
    }

    private fun onError(throwable: Throwable) {
        Log.d("Error", "Error")
    }
}