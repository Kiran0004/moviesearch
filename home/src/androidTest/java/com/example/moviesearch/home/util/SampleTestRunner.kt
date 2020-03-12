package com.example.moviesearch.home.util

import android.app.Application
import android.content.Context
import com.example.moviesearch.home.TestApp


class SampleTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}
