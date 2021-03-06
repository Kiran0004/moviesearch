package com.example.moviesearch.di.modules

import com.example.moviesearch.core.model.api.OmdbApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesOmdbApi(retrofit: Retrofit) = retrofit.create(OmdbApi::class.java)

}
