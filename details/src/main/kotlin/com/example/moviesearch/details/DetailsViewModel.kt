package com.example.moviesearch.details

import android.arch.lifecycle.MutableLiveData
import com.example.moviesearch.core.base.BaseViewModel
import com.example.moviesearch.core.model.api.ApiResponse
import com.example.moviesearch.core.model.domain.DetailsResponseVO
import com.example.moviesearch.core.model.interactor.OmdbUseCase
import com.example.moviesearch.core.util.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kiran.
 */
class DetailsViewModel
@Inject constructor(useCase: OmdbUseCase) : BaseViewModel(useCase) {

    private val viewResponse = MutableLiveData<ApiResponse<DetailsResponseVO>>()

    fun requestDetails(imdbID: String) {
        loadResultDetails(useCase.requestDetailsByImdbID(imdbID))
    }

    fun getResponse(): MutableLiveData<ApiResponse<DetailsResponseVO>> {
        return viewResponse
    }

    private fun loadResultDetails(single: Single<DetailsResponseVO>) {
        disposables.add(single
                .subscribeOn(RxSchedulers.io())
                .observeOn(RxSchedulers.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { detailsResponse -> viewResponse.value = ApiResponse.success(detailsResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

}