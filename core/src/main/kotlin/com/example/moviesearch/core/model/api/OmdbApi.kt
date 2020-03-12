package com.example.moviesearch.core.model.api

import com.example.moviesearch.core.BuildConfig
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.core.util.Constants.BASE_API_KEY
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kiran.
 */
interface OmdbApi {

    @GET(BuildConfig.ENDPOINT + BASE_API_KEY)
    fun searchByQuery(@Query("s") query: String): Single<SearchResponseVO>

    @GET(BuildConfig.ENDPOINT + BASE_API_KEY)
    fun getDetailsByImdbID(@Query("i") imdbId: String): Single<JsonObject>

}