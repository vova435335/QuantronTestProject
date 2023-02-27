package dev.vladimir.details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.details.databinding.FragmentMediaDetailsBinding

class MediaDetailsFragment : BaseFragment<FragmentMediaDetailsBinding>() {

    private val viewModel by viewModels<MediaDetailsViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMediaDetailsBinding {
        TODO("Not yet implemented")
    }

    private fun observeViewModel() {
        viewModel.mediaDetailsState.observe(this) {

        }
    }
}