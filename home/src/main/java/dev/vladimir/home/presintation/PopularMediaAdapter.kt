package dev.vladimir.home.presintation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.home.databinding.ItemPopularMovieBinding
import dev.vladimir.home.domain.model.Media
import dev.vladimir.home.domain.model.MediaType

class PopularMediaAdapter(
    private val openDetails: (mediaId: String, mediaType: MediaType) -> Unit,
) :
    PagingDataAdapter<Media, PopularMediaAdapter.Holder>(PopularMediaDiffUtilCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val media = getItem(position) ?: return

        with(holder.binding) {
            Glide.with(itemPopularMoviePosterIv)
                .load(media.posterPath)
                .into(itemPopularMoviePosterIv)
            itemPopularMovieTitleTv.text = media.title
            itemMediaContainerCl.setOnClickListener {
                openDetails(
                    media.id.toString(),
                    media.mediaType
                )
            }
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

class PopularMediaDiffUtilCallback : DiffUtil.ItemCallback<Media>() {

    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}
