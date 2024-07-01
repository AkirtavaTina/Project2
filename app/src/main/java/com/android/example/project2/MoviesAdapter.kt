import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.project2.Movies
import com.android.example.project2.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class MoviesAdapter(


    private var movies: List<Movies>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(movie: Movies) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.path}")
                .transform(CenterCrop())
                .into(poster)
            val title : TextView = itemView.findViewById(R.id.title)
            title.text = movie.name

            val overview : TextView = itemView.findViewById(R.id.overview)
            overview.text = movie.overview

            val rating : TextView = itemView.findViewById(R.id.rating)
            rating.text = "imdb " + movie.vote
        }
    }
}