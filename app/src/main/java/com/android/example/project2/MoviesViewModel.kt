package com.android.example.project2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movies>>()
    val movies: LiveData<List<Movies>> get() = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        repository.getMovies(
            onSuccess = { movies->
                _movies.postValue(movies)
            },
            onError = {
                println("Error fetching movies")
            }
        )
    }
}