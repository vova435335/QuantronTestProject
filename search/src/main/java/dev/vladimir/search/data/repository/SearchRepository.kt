package dev.vladimir.search.data.repository

import android.util.Log
import dev.vladimir.search.data.MovieApi
import dev.vladimir.search.data.mappers.MediaMappers
import dev.vladimir.search.domain.model.Movie
import dev.vladimir.search.domain.repository.ISearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val mediaMappers: MediaMappers,
) : ISearchRepository {

    override suspend fun searchMovie(page: Int, query: String): List<Movie> =
        withContext(Dispatchers.IO) {
            Log.d("qqq", "TEST")
            mediaMappers.mapMedias(movieApi.searchMovies(page, query))
        }
}
