package com.example.moviesearch.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by Kiran.
 */
data class DetailsMovieReponseVO(
        @SerializedName("DVD")
        val dvd: String? = "",
        @SerializedName("BoxOffice")
        val boxOffice: String? = "",
        @SerializedName("Website")
        val website: String? = ""
) : DetailsResponseVO()
