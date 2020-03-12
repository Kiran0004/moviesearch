package com.example.moviesearch.core.base

import android.content.Context
import android.content.Intent

/**
 * Created by Kiran.
 */
interface DataDetailsModel {

    sealed class DataDetailBO {
        object DetailView : DataDetailBO()
        object ListView : DataDetailBO()
    }

    fun getIntent(context: Context, dataDetail: DataDetailBO): Intent?
}