package com.example.moviesearch.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by Kiran.
 */
data class RatingsVO(
        @SerializedName("Source")
        val source: String? = "",
        @SerializedName("Value")
        val value: String? = ""
)