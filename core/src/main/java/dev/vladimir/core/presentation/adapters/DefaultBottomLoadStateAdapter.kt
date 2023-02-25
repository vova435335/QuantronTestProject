package dev.vladimir.core.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.vladimir.core.databinding.ViewBottomLoaderBinding

class DefaultBottomLoadStateAdapter(
    private val tryAgainAction: () -> Unit,
) : LoadStateAdapter<DefaultBottomLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        with(holder.binding) {
            loaderMessageTv.isVisible = loadState is LoadState.Error
            loaderTryAgainButton.isVisible = loadState is LoadState.Error
            loaderPb.isVisible = loadState is LoadState.Loading

            loaderTryAgainButton.setOnClickListener { tryAgainAction.invoke() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewBottomLoaderBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(
        internal val binding: ViewBottomLoaderBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}
