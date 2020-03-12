package com.example.moviesearch.model

import android.content.Context
import android.content.Intent
import com.example.moviesearch.core.base.DataDetailsModel
import com.example.moviesearch.details.DetailsActivity

/**
 * Created by Kiran.
 */
object DataDetailsModelBO : DataDetailsModel {

    override fun getIntent(context: Context, dataDetail: DataDetailsModel.DataDetailBO): Intent? {
        val clazz: Class<*>? = when (dataDetail) {
            DataDetailsModel.DataDetailBO.DetailView -> DetailsActivity::class.java
            DataDetailsModel.DataDetailBO.ListView -> null // not necessarily
        }
        return if (clazz == null) null
               else Intent(context, clazz)
    }

}

