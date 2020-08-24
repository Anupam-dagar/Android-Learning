package com.example.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import com.example.base.data.entity.Movie
import com.example.base.data.entity.MovieResponse
import com.example.base.data.repositories.MovieRepository
import com.example.base.di.components.BaseComponent
import com.example.ui.data.api.TMDBApi
import com.example.ui.viewmodel.MainFragmentViewModel
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import org.hamcrest.CoreMatchers.instanceOf
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@RunWith(JUnit4::class)
class UiTest {

    var baseComponent = mockk<BaseComponent>(relaxed = true)

    @Inject
    lateinit var tmdbApi: TMDBApi


    @Inject
    lateinit var viewModel: MainFragmentViewModel

    @get:Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val observer: Observer<List<Movie>> = mockk()

    @Before
    fun setup() {
        val component = DaggerTestUiComponent.builder()
            .apiModule(TestUiModule())
            .baseComponent(baseComponent)
            .build()
        component.into(this)
    }

    @Test
    fun testPopularMoviesApi() {
        val testapikey = "testapikey"
        val movieResponse = MovieResponse(
            1, 1, 1,
            listOf(
                Movie(
                    1,
                    1.1,
                    "/abc.jpg",
                    1,
                    "/def.jpg",
                    "test",
                    1.2,
                    "lorem ipsum",
                    "1234-56-78",
                    "null"
                )
            )
        )

        every { tmdbApi.getPopularMovies(any()) } returns Observable.just(movieResponse)

        val result = tmdbApi.getPopularMovies(testapikey)

        result.test().assertValue(movieResponse)
    }

    @Test
    fun testTopRatedMoviesApi() {
        val testapikey = "testapikey"
        val movieResponse = MovieResponse(
            1, 1, 1,
            listOf(
                Movie(
                    1,
                    1.1,
                    "/abc.jpg",
                    1,
                    "/def.jpg",
                    "test",
                    1.2,
                    "lorem ipsum",
                    "1234-56-78",
                    "null"
                )
            )
        )

        every { tmdbApi.getTopRatedMovies(any()) } returns Observable.just(movieResponse)

        val result = tmdbApi.getTopRatedMovies(testapikey)

        result.test().assertValue(movieResponse)
    }

    @Test
    fun testAddPopularMoviesToDatabaase() {
        val movie = Movie(
            1,
            1.1,
            "/abc.jpg",
            1,
            "/def.jpg",
            "test",
            1.2,
            "lorem ipsum",
            "1234-56-78",
            "popular"
        )
        viewModel.addMovies(listOf(movie, movie))
        verify(exactly = 1) { viewModel.addMovies(listOf(movie, movie)) }
    }

    @Test
    fun testAddPopularMovieToDatabaase() {
        val movie = Movie(
            1,
            1.1,
            "/abc.jpg",
            1,
            "/def.jpg",
            "test",
            1.2,
            "lorem ipsum",
            "1234-56-78",
            "popular"
        )
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        verify(exactly = 4) { viewModel.addMovie(movie) }
        verify(atLeast = 1) { viewModel.addMovie(movie) }
        verify(atMost = 4) { viewModel.addMovie(movie) }
    }

    @Test
    fun testAddTopRatedMoviesToDatabaase() {
        val movie = Movie(
            1,
            1.1,
            "/abc.jpg",
            1,
            "/def.jpg",
            "test",
            1.2,
            "lorem ipsum",
            "1234-56-78",
            "toprated"
        )
        viewModel.addMovies(listOf(movie, movie))
        verify(exactly = 1) { viewModel.addMovies(listOf(movie, movie)) }
    }

    @Test
    fun testAddTopRatedMovieToDatabaase() {
        val movie = Movie(
            1,
            1.1,
            "/abc.jpg",
            1,
            "/def.jpg",
            "test",
            1.2,
            "lorem ipsum",
            "1234-56-78",
            "toprated"
        )
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        viewModel.addMovie(movie)
        verify(exactly = 4) { viewModel.addMovie(movie) }
        verify(atLeast = 1) { viewModel.addMovie(movie) }
        verify(atMost = 4) { viewModel.addMovie(movie) }
    }

    @Test
    fun testGetMovieFromDatabase() {
        val movie = Movie(
            1,
            1.1,
            "/abc.jpg",
            1,
            "/def.jpg",
            "test",
            1.2,
            "lorem ipsum",
            "1234-56-78",
            "popular"
        )
        viewModel.addMovie(movie)
        val dbmovie = viewModel.getMovie(1)
        Assert.assertThat(dbmovie, instanceOf(Movie::class.java))
    }

//    @Test
//    fun testGetPopularMoviesFromDatabase() {
//        val movie = Movie(
//            1,
//            1.1,
//            "/abc.jpg",
//            1,
//            "/def.jpg",
//            "test",
//            1.2,
//            "lorem ipsum",
//            "1234-56-78",
//            "popular"
//        )
//        viewModel.addMovies(listOf(movie, movie))
//        println("${viewModel.getMovies("popular").getOrAwaitValue()}")
//    }
//
//    fun <T> LiveData<T>.getOrAwaitValue(
//        time: Long = 2,
//        timeUnit: TimeUnit = TimeUnit.SECONDS
//    ): T {
//        var data: T? = null
//        val latch = CountDownLatch(1)
//        val observer = object : Observer<T> {
//            override fun onChanged(o: T?) {
//                data = o
//                latch.countDown()
//                this@getOrAwaitValue.removeObserver(this)
//            }
//        }
//
//        this.observeForever(observer)
//
//        // Don't wait indefinitely if the LiveData is not set.
//        if (!latch.await(time, timeUnit)) {
//            throw TimeoutException("LiveData value was never set.")
//        }
//
//        @Suppress("UNCHECKED_CAST")
//        return data as T
//    }
}