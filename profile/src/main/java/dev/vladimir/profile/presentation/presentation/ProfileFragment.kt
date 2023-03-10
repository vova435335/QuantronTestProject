package dev.vladimir.profile.presentation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.profile.R
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

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkAuth()
    }

    private fun initViews(profile: Profile) {
        with(binding) {
            profileNameValueTv.text = profile.name
            profileUsernameValueTv.text = profile.username
            Glide.with(profileAvatarIv)
                .load(profile.avatar_path)
                .circleCrop()
                .placeholder(R.drawable.ic_person)
                .into(profileAvatarIv)
        }
    }

    private fun observeViewModel() {
        viewModel.isAuthState.observe(this) { isAuth -> navigateToAuth(isAuth) }
        viewModel.profileState.observe(this) { state -> observeLoadState(state) }
    }

    private fun observeLoadState(state: LoadState<Profile>) {
        with(binding) {
            profilePb.isVisible = state is LoadState.Loading
            profileErrorView.root.isVisible = state is LoadState.Error
            profileContentCl.isVisible = state is LoadState.Success

            if (state is LoadState.Success) initViews(state.data ?: Profile())
            if (state is LoadState.Error) {
                profileErrorView.errorMessageTv.text = state.message
                profileErrorView.errorTryAgainButton.setOnClickListener {
                    viewModel.getProfile()
                    profilePb.isVisible = true
                    profileErrorView.root.isVisible = false
                }
            }
        }
    }

    private fun navigateToAuth(isAuth: Boolean) {
        if (!isAuth){
            val request = NavDeepLinkRequest.Builder
                .fromUri(getString(dev.vladimir.core.R.string.navigate_to_auth).toUri())
                .build()
            findNavController().navigate(request)
        }
    }
}
