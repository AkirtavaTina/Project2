import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.project2.Movies
import com.android.example.project2.databinding.MovieCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class MoviesAdapter(

    private var movies: MutableList<Movies>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun appendMovies(movies: List<Movies>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(this.movies.size, movies.size-1)
    }

    inner class MovieViewHolder(private val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            with(binding) {
                Glide.with(root.context)
                    .load("https://image.tmdb.org/t/p/w342${movie.path}")
                    .transform(CenterCrop())
                    .into(itemMoviePoster)
                title.text = movie.name
                overview.text = movie.overview
                rating.text = "imdb ${movie.vote}"
            }
        }
    }
}