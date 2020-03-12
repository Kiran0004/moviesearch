package com.example.moviesearch.di.modules

import com.example.moviesearch.details.DetailsActivity
import com.example.moviesearch.details.DetailsModule
import com.example.moviesearch.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector // TODO: test empty module
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailsModule::class])
    internal abstract fun bindDetailsActivity(): DetailsActivity

}
