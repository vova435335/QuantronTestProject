package dev.vladimir.details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.details.databinding.ItemActorBinding
import dev.vladimir.details.domain.model.Actor

class ActorsAdapter : ListAdapter<Actor, ActorsAdapter.Holder>(ActorsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemActorBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val actor = getItem(position)

        with(holder.binding) {
            Glide.with(itemActorPosterIv)
                .load(actor.actorPhotoPath)
                .into(itemActorPosterIv)
            itemActorTitleTv.text = actor.name
        }
    }

    inner class Holder(
        internal val binding: ItemActorBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}


class ActorsDiffUtilCallback : DiffUtil.ItemCallback<Actor>() {

    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }
}
