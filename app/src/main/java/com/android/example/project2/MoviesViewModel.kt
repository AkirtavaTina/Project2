package com.android.example.project2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movies>>()
    val movies: LiveData<List<Movies>> get() = _movies

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    fun loadMovies() {
        repository.getMovies(
            onSuccess = {
                _movies.value = it
                _error.value = false
            },
            onError = {
                _error.value = true
            }
        )
    }
}