package com.example.moviesearch.core.util

import com.example.moviesearch.core.model.domain.SearchResponseVO

/**
 * Created by Kiran.
 */
interface UIListeners {

    interface OnClickListener {
        fun onClick(resultVO: SearchResponseVO.ResultVO)
    }

}