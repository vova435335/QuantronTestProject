package dev.vladimir.home.presintation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.popularMoviesState.observe(this) {
            popularMovieAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecycler() {
        popularMovieAdapter = PopularMovieAdapter()

        binding.homeRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = popularMovieAdapter
        }
    }
}
