package com.example.moviesearch.core.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.moviesearch.core.model.interactor.OmdbUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Kiran.
 */
open class BaseViewModel(protected val useCase: OmdbUseCase) : ViewModel() {

    protected val disposables = CompositeDisposable()

    val loadingStatus = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}