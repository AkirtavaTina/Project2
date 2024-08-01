package com.android.example.project2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviesRepository (private val api: MyApi){

    fun getMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getMovies(page = page)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null)
                            onSuccess.invoke(responseBody.movies)
                    } else onError.invoke()
                } catch (e: Exception) {
                    onError.invoke()
                }
            }
        }
    }
}