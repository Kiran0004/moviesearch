package com.example.moviesearch.home

import android.arch.lifecycle.MutableLiveData
import com.example.moviesearch.core.base.BaseViewModel
import com.example.moviesearch.core.model.interactor.OmdbUseCase
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.core.model.api.ApiResponse
import com.example.moviesearch.core.util.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kiran.
 */

// extends from AndroidViewModel
class HomeViewModel
@Inject constructor(useCase: OmdbUseCase) : BaseViewModel(useCase) {

    private val viewResponse = MutableLiveData<ApiResponse<SearchResponseVO>>()

    fun searchByQuery(query: String) {
        loadResultList(useCase.requestSearchByQuery(query))
    }

    fun getResponse(): MutableLiveData<ApiResponse<SearchResponseVO>> {
        return viewResponse
    }

    private fun loadResultList(single: Single<SearchResponseVO>) {
        disposables.add(single
                .subscribeOn(RxSchedulers.io())
                .observeOn(RxSchedulers.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { viewResponse.value = ApiResponse.success(it) },
                            { viewResponse.value = ApiResponse.error(it) })
        )
    }

}