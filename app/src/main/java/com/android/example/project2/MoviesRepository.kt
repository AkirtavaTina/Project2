package com.android.example.project2
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class MoviesRepository (private val movieDao: MovieDao, private val api:MyApi, ){

    val allMovies: Flow<List<Movies>> = movieDao.getAllMovies()

    fun getMovies(
        page: Int = 1,
//        onSuccess: (movies: List<Movies>) -> Unit
//        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getMovies(page = page)
            try {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        movieDao.addMovie(responseBody.movies)
//                        onSuccess.invoke(responseBody.movies)
                        Log.d("success2", "movie")
                    }
                }
                else Log.d("failed1", "movie")
            } catch (e: Exception) {
                Log.d("failed2", "movie")            }
        }
    }
}