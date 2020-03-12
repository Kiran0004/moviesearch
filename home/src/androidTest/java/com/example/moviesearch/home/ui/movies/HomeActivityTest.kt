package com.example.moviesearch.home.ui.movies

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.home.HomeActivity
import com.example.moviesearch.home.HomeAdapter
import com.example.moviesearch.home.HomeViewModel
import com.example.moviesearch.home.R
import com.google.gson.Gson
import mm.com.sumyat.archiecture_sample.util.CountingAppExecutorsRule
import mm.com.sumyat.archiecture_sample.util.EspressoTestUtil
import mm.com.sumyat.archiecture_sample.util.TaskExecutorWithIdlingResourceRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(HomeActivity::class.java, true, true)
    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    private lateinit var mockBindingAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel
    private val results = MutableLiveData<Resource<List<SearchResponseVO.ResultVO>>>()
    private val loadMoreStatus = MutableLiveData<HomeViewModel>()

    @Before
    fun init() {
        viewModel = Mockito.mock(HomeViewModel::class.java)
        Mockito.doReturn(loadMoreStatus).`when`(viewModel).loadMoreStatus
        Mockito.`when`(viewModel.results).thenReturn(results)

        mockBindingAdapter = Mockito.mock(HomeAdapter::class.java)

        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun testLoading() {
        results.postValue(Resource.loading(null))
        Espresso.onView(ViewMatchers.withId(R.id.loadingIndicator))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_noData))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun search() {
        Espresso.onView(ViewMatchers.withId(R.id.loadingIndicator))
        viewModel.setQuery("foo")
        Mockito.verify(viewModel).setQuery("foo")
        results.postValue(Resource.loading(null))
        Espresso.onView(ViewMatchers.withId(R.id.loadingIndicator))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadResults() {
        val movie = TestUtil.createMovie("movie")
        results.postValue(Resource.success(arrayListOf(movie)))
        Espresso.onView(listMatcher().atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("movie"))))
        Espresso.onView(ViewMatchers.withId(R.id.loadingIndicator))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun dataWithLoading() {
        val movie = TestUtil.createMovie("movie")
        results.postValue(Resource.loading(arrayListOf(movie)))
        Espresso.onView(listMatcher().atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("movie"))))
    }

    @Test
    fun error() {
        results.postValue(Resource.error("failed to load", null))
        Espresso.onView(ViewMatchers.withId(R.id.tv_noData))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadMore() {
        val movies = TestUtil.createMovies(50,"movie")
        results.postValue(Resource.success(movies))
        val action = RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(49)
        Espresso.onView(ViewMatchers.withId(R.id.rvResultSearch)).perform(action)
        Espresso.onView(listMatcher().atPosition(49))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rvResultSearch)
    }


}