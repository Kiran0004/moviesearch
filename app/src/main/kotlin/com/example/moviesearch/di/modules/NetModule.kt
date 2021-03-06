package com.example.moviesearch.di.modules

import android.preference.PreferenceManager
import com.example.moviesearch.MovieSearchApplication
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    val TIMEOUT_SECONDS = 60

    @Provides
    @Singleton
    fun provideSharedPreferences(app: MovieSearchApplication) = PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun provideHttpCache(app: MovieSearchApplication): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .addNetworkInterceptor(logging)
                .cache(cache)
                .build()
    }

}
