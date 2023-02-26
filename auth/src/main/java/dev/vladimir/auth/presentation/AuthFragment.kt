package dev.vladimir.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.auth.R
import dev.vladimir.auth.databinding.FragmentAuthBinding
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.core.presentation.model.LoadState

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(binding) {
            authButton.setOnClickListener {
                viewModel.login(
                    login = authLoginTet.text.toString(),
                    password = authPasswordTet.text.toString()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.authState.observe(this) { state ->
            with(binding) {
                authPb.isVisible = state is LoadState.Loading
                authButton.isEnabled = state !is LoadState.Loading
                authButton.text = if (state is LoadState.Loading) {
                    ""
                } else {
                    getString(R.string.auth_button_text)
                }

                if (state is LoadState.Error) showToast(state.message.toString())
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
