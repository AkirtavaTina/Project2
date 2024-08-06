package com.android.example.project2
import MoviesAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.project2.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList


class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var movies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesLayoutMgr: LinearLayoutManager
    private lateinit var moviesRepository : MoviesRepository
    private var moviesPage = 1
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel
        movies = binding.movies
        moviesLayoutMgr = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        movies.layoutManager = moviesLayoutMgr
        moviesAdapter = MoviesAdapter(mutableListOf())
        movies.adapter = moviesAdapter

//        getMovies()
        moviesViewModel.allMovies.observe(viewLifecycleOwner) {
            moviesAdapter.appendMovies(it)
        }


    }

//    private fun getMovies(){
////        moviesRepository.getMovies(moviesPage,
////            ::onMoviesFetched)
//       moviesViewModel.loadMovies(moviesPage, ::onMoviesFetched)
//    }
//
//    private fun onMoviesFetched(movies: List<Movies>) {
//        moviesAdapter.appendMovies(movies)
//        Log.d("fetched", "movie")
//        attachMoviesOnScrollListener()
//    }
//
//    private fun attachMoviesOnScrollListener() {
//        movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val totalItemCount = moviesLayoutMgr.itemCount
//                val visibleItemCount = moviesLayoutMgr.childCount
//                val firstVisibleItem = moviesLayoutMgr.findFirstVisibleItemPosition()
//
//                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                    movies.removeOnScrollListener(this)
//                    moviesPage++
//                    getMovies()
//                }
//            }
//        })
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}