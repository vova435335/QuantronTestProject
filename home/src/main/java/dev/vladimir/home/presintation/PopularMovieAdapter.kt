package dev.vladimir.home.presintation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.core.R.drawable
import dev.vladimir.home.databinding.ItemPopularMovieBinding
import dev.vladimir.home.domain.model.Movie

class PopularMovieAdapter :
    PagingDataAdapter<Movie, PopularMovieAdapter.Holder>(PopularMoviesDiffUtilCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position) ?: return

        with(holder.binding) {
            Glide.with(itemPopularMoviePosterIv)
                .load(movie.posterPath)
                .placeholder(drawable.movie_placeholder)
                .into(itemPopularMoviePosterIv)
            itemPopularMovieTitleTv.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularMovieBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(internal val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class PopularMoviesDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
