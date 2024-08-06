package com.android.example.project2
import MoviesAdapter
import android.os.Bundle
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
        movies.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        moviesAdapter = MoviesAdapter(mutableListOf())
        movies.adapter = moviesAdapter


        moviesViewModel.moviesList.observe(viewLifecycleOwner) {
            moviesAdapter.submitData(lifecycle, it)
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}