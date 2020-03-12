package com.example.moviesearch.di

import com.example.moviesearch.MovieSearchApplication
import com.example.moviesearch.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    NetModule::class,
    RetrofitModule::class,
    ApiModule::class,
    ScreenRouterModule::class,
    BuildersModule::class]
)
interface AppComponent : AndroidInjector<MovieSearchApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieSearchApplication>()

}
