
package com.example.moviesearch


import android.graphics.Movie
import com.example.moviesearch.core.model.domain.SearchResponseVO
import java.util.concurrent.ThreadLocalRandom


object TestUtil {

    fun createMovieResult(count: Int, title: String): List<SearchResponseVO.ResultVO> {
        return (0 until count).map {
            createMovieResponse(title)
        }
    }

    fun movieResultToMovie(list: List<SearchResponseVO.ResultVO>): List<SearchResponseVO.ResultVO> {
        return list.map {
            SearchResponseVO.ResultVO(
                it.title,
                it.year,
                it.imdbID,
                it.poster,
                it.type
            )
        }
    }

    fun createMovieResponse(title: String) = SearchResponseVO.ResultVO(
        43.929,
        7271,
        false,
        "/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
       "Titanic"
    )

    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }
}
