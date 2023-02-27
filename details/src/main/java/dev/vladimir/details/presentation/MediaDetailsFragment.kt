package dev.vladimir.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.details.R
import dev.vladimir.details.databinding.FragmentMediaDetailsBinding
import dev.vladimir.details.domain.model.MediaDetailsModel

@AndroidEntryPoint
class MediaDetailsFragment : BaseFragment<FragmentMediaDetailsBinding>() {

    private val viewModel by viewModels<MediaDetailsViewModel>()

    private val argument by lazy { arguments?.getString("media_id") }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMediaDetailsBinding =
        FragmentMediaDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMediaDetails(argument ?: "")
        observeViewModel()
    }

    private fun initViews(media: MediaDetailsModel) {
        with(binding) {
            Glide.with(mediaDetailsPosterIv)
                .load(media.posterPath)
                .placeholder(R.drawable.ic_broken_image)
                .into(mediaDetailsPosterIv)

            mediaDetailsTitleTv.text = media.title
            mediaDetailsReleaseDateValueTv.text = media.releaseDate
            mediaDetailsRuntimeValueTv.text = media.runtime
            mediaDetailsOverviewTv.text = media.overview

            if (mediaDetailsGenresCg.size == 0) {
                media.genres.forEach { genre ->
                    val chip = Chip(requireContext())
                    chip.text = genre
                    mediaDetailsGenresCg.addView(chip)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.mediaDetailsState.observe(this) { state -> observeLoadState(state) }
    }

    private fun observeLoadState(state: LoadState<MediaDetailsModel>) {
        with(binding) {
            mediaDetailsPb.isVisible = state is LoadState.Loading
            mediaDetailsErrorView.root.isVisible = state is LoadState.Error
            mediaDetailsContentCl.isVisible = state is LoadState.Success

            if (state is LoadState.Success) initViews(state.data ?: MediaDetailsModel())
            if (state is LoadState.Error) {
                mediaDetailsErrorView.errorMessageTv.text = state.message
                mediaDetailsErrorView.errorTryAgainButton.setOnClickListener {
                    viewModel.getMediaDetails(argument ?: "")
                    mediaDetailsPb.isVisible = true
                    mediaDetailsErrorView.root.isVisible = false
                }
            }
        }
    }
}