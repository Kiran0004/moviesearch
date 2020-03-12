package com.example.moviesearch.core.model.repository

import com.example.moviesearch.core.model.api.OmdbApi
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.google.gson.JsonObject
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kiran.
 */
class OmdbRepository
@Inject constructor() {

    @Inject lateinit var omdbApi: OmdbApi

    fun searchByQuery(query: String): Single<SearchResponseVO> {
        return omdbApi.searchByQuery(query)
    }

    fun getDetailsByImdbID(imdbId: String): Single<JsonObject> {
        return omdbApi.getDetailsByImdbID(imdbId)
    }

}