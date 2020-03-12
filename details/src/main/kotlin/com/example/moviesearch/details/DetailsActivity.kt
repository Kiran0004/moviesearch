package com.example.moviesearch.details

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.moviesearch.core.base.BaseActivity
import com.example.moviesearch.core.model.api.ApiResponse
import com.example.moviesearch.core.model.domain.DetailsResponseVO
import com.example.moviesearch.core.util.Constants.KEY_IMDB_ID
import com.example.moviesearch.core.util.loadGlide
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

/**
 * Created by Kiran.
 */
class DetailsActivity(override val layoutResId: Int = R.layout.activity_details) : BaseActivity() {

    @Inject lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestDetails(intent.getStringExtra(KEY_IMDB_ID))
        observeLoadingStatus(viewModel, clDetails, loadingIndicator)
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel.getResponse().observe(this, Observer<ApiResponse<DetailsResponseVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        if (response.data is DetailsResponseVO) {
            val vo = response.data as DetailsResponseVO
            initLayout(vo)
        } else super.processResponse(response)
    }

    private fun initLayout(vo: DetailsResponseVO) {
        ivPoster.loadGlide(vo.poster, false)
        tvTitle.text = vo.title
        tvDescription.text = "${"Type"}: ${vo.type} - ${"Year"}: ${vo.year}"
        tvGenre.text ="${vo.director} ${","} ${vo.production}"
        tvActors.text = vo.actors
        tvLanguage.text = vo.language
        tvAwards.text = vo.awards
    }

}

