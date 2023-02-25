package dev.vladimir.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.profile.R

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.toString()
    }
}