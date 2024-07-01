package com.android.example.project2

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val api: MyApi
    private const val BASE_URL =
        "https://api.themoviedb.org/3/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MyApi::class.java)
    }

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