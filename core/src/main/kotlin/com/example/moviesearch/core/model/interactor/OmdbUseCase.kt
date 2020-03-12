package com.example.moviesearch.core.model.interactor

import com.example.moviesearch.core.base.BaseUseCase
import com.example.moviesearch.core.model.domain.DetailsResponseVO
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.core.model.repository.OmdbRepository
import com.example.moviesearch.core.util.deserializeDetails
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kiran.
 */
class OmdbUseCase
@Inject constructor(private val repository: OmdbRepository) : BaseUseCase() {

    fun requestSearchByQuery(query: String): Single<SearchResponseVO> {
        return repository.searchByQuery(query)
    }

    fun requestDetailsByImdbID(imdbID: String): Single<DetailsResponseVO> {
        return repository.getDetailsByImdbID(imdbID)
                          .map { it.deserializeDetails(gson) }
    }

}