package com.example.moviesearch.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.moviesearch.core.base.BaseActivity
import com.example.moviesearch.core.base.DataDetailsModel
import com.example.moviesearch.core.model.api.ApiResponse
import com.example.moviesearch.core.model.api.Status.*
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.core.util.Constants.KEY_IMDB_ID
import com.example.moviesearch.core.util.UIListeners
import com.example.moviesearch.core.util.closeKeyboard
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity(override val layoutResId: Int = R.layout.activity_home) :
        BaseActivity(), SearchView.OnQueryTextListener, UIListeners.OnClickListener {

    @Inject lateinit var viewModel: HomeViewModel
    @Inject lateinit var dataDetailsModel: DataDetailsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoadingStatus(viewModel, rvResultSearch, loadingIndicator)
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel.getResponse().observe(this, Observer<ApiResponse<SearchResponseVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        when (response.status) {
            SUCCESS -> {
                if (response.data is SearchResponseVO) {
                        var dataRes = response.data as SearchResponseVO
                        if(dataRes.resultList.size>0){
                            val adapter = HomeAdapter(response.data as SearchResponseVO, this, this)
                            rvResultSearch.layoutManager = LinearLayoutManager(this)
                            rvResultSearch.setHasFixedSize(true)
                            rvResultSearch.adapter = adapter
                            tv_noData.visibility = View.GONE
                        }else{
                            rvResultSearch.adapter = null
                            rvResultSearch.visibility = View.GONE
                            tv_noData.text = "No Data found"
                            tv_noData.visibility = View.VISIBLE
                        }

                } else {
                    super.processResponse(response)
                }
            }
            ERROR -> {
                Log.e(localClassName, response.error?.message, response.error)
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(resultVO: SearchResponseVO.ResultVO) {
        val intent: Intent? = dataDetailsModel.getIntent(this, DataDetailsModel.DataDetailBO.DetailView)
        intent?.let {
            intent.apply { putExtra(KEY_IMDB_ID, resultVO.imdbID) }
                  .run { startActivity(this) }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                this@HomeActivity.closeKeyboard()
                processResponse(ApiResponse(LOADING, data = null, isLoading = false))
                return true
            }
        })

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        closeKeyboard()
        viewModel.searchByQuery(query)
        return true
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}
