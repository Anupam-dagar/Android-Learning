package com.example.ui

import com.example.base.data.entity.Movie
import com.example.base.data.entity.MovieResponse
import com.example.base.di.components.BaseComponent
import com.example.ui.data.api.TMDBApi
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class UiTest {

    var baseComponent = mockk<BaseComponent>()

    @Inject
    lateinit var tmdbApi: TMDBApi

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

        every { tmdbApi.getPopularMovies(testapikey) } returns Observable.just(movieResponse)

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

        every { tmdbApi.getTopRatedMovies(testapikey) } returns Observable.just(movieResponse)

        val result = tmdbApi.getTopRatedMovies(testapikey)

        result.test().assertValue(movieResponse)
    }
}