package com.example.moviesearch.core.base

import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by Kiran.
 */
abstract class BaseUseCase {

    @Inject protected lateinit var gson: Gson

}
