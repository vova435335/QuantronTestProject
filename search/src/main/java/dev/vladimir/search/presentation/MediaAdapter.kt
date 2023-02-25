package dev.vladimir.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.core.R.drawable
import dev.vladimir.search.databinding.ItemHeaderBinding
import dev.vladimir.search.databinding.ItemMediaBinding
import dev.vladimir.search.domain.model.Media

private const val HEADER_TYPE = 0
private const val MOVIE_TYPE = 1

class MediaAdapter :
    PagingDataAdapter<Media, RecyclerView.ViewHolder>(PopularMoviesDiffUtilCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val media = getItem(position) ?: return

        when {
            holder is MediaHolder && media is Media.Movie -> {
                with(holder.binding) {
                    Glide.with(itemMediaPosterIv)
                        .load(media.posterPath)
                        .placeholder(drawable.movie_placeholder)
                        .into(itemMediaPosterIv)
                    itemMediaTitleTv.text = media.title
                }
            }
            holder is HeaderHolder && media is Media.Header -> {
                with(holder.binding) {
                    itemHeaderTv.text = media.title
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position) ?: return -1

        return when (item) {
            is Media.Header -> HEADER_TYPE
            is Media.Movie -> MOVIE_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            MOVIE_TYPE -> {
                val binding = ItemMediaBinding.inflate(inflater, parent, false)
                MediaHolder(binding)
            }
            else -> {
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                HeaderHolder(binding)
            }
        }
    }

    inner class MediaHolder(internal val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class HeaderHolder(internal val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class PopularMoviesDiffUtilCallback : DiffUtil.ItemCallback<Media>() {

    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return when {
            oldItem is Media.Movie && newItem is Media.Movie -> oldItem.id == newItem.id
            oldItem is Media.Header && newItem is Media.Header -> oldItem.title == newItem.title
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}
