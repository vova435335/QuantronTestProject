package dev.vladimir.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.core.R.drawable
import dev.vladimir.search.databinding.ItemMediaBinding
import dev.vladimir.search.domain.model.Movie

class MediaAdapter :
    PagingDataAdapter<Movie, MediaAdapter.Holder>(PopularMoviesDiffUtilCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position) ?: return

        with(holder.binding) {
            Glide.with(itemMediaPosterIv)
                .load(movie.posterPath)
                .placeholder(drawable.movie_placeholder)
                .into(itemMediaPosterIv)
            itemMediaTitleTv.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMediaBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(internal val binding: ItemMediaBinding) :
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