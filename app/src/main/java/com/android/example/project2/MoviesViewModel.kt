package com.android.example.project2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    val allMovies: LiveData<List<Movies>> get() = repository.allMovies.asLiveData()

    init {
        loadMovies()
    }

     fun loadMovies(
//         page:Int,
//         onSuccess: (movies: List<Movies>)-> Unit
     ) {
       Log.d("movie", "here")
         repository.getMovies(
//             page,
//             onSuccess
         )
    }
}