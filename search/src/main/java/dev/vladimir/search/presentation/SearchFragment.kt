package dev.vladimir.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.search.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}