package dev.vladimir.profile.presentation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.profile.databinding.FragmentProfileBinding
import dev.vladimir.profile.presentation.domain.model.Profile

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        observeViewModel()

//        (requireActivity() as AppNavigator).navigateToAuth()
    }

    private fun initData() {
        viewModel.getProfile()
    }

    private fun initViews(profile: Profile) {
        with(binding) {
            profileNameValueTv.text = profile.name
            profileUsernameValueTv.text = profile.username
        }
    }

    private fun observeViewModel() {
        viewModel.profileState.observe(this) { state ->
            with(binding) {
                profilePb.isVisible = state is LoadState.Loading
                profileErrorView.root.isVisible = state is LoadState.Error
                profileContentCl.isVisible = state is LoadState.Success

                if (state is LoadState.Success) initViews(state.data ?: Profile())
                if (state is LoadState.Error) profileErrorView.errorMessageTv.text = state.message
            }
        }
    }
}
