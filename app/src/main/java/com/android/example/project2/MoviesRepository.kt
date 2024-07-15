package com.android.example.project2

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesRepository (private val api: MyApi){

    fun getMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: () -> Unit
    ) {
        api.getMovies(page = page).enqueue(object : Callback<GetMoviesResponse> {
            override fun onResponse(
                call: Call<GetMoviesResponse>,
                response: Response<GetMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        onSuccess.invoke(responseBody.movies)
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}