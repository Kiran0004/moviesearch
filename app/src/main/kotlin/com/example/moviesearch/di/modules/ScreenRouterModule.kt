package com.example.moviesearch.di.modules

import com.example.moviesearch.core.base.DataDetailsModel
import com.example.moviesearch.model.DataDetailsModelBO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScreenRouterModule {

    @Provides
    @Singleton
    fun provideScreenRouter(): DataDetailsModel = DataDetailsModelBO

}