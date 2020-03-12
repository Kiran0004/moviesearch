package com.example.moviesearch.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by Kiran.
 */
data class DetailsSeriesResponseVO(
        @SerializedName("totalSeasons")
        val totalSeasons: String? = ""
) : DetailsResponseVO()
