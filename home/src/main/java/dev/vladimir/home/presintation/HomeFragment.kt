package dev.vladimir.home.presintation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.adapters.DefaultBottomLoadStateAdapter
import dev.vladimir.home.R
import dev.vladimir.home.databinding.FragmentHomeBinding

private const val SPAN_COUNT = 3

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var popularMovieBottomAdapter: DefaultBottomLoadStateAdapter

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        initRecycler()
        initSwipeRefresh()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.popularMoviesState.observe(this) {
            popularMovieAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecycler() {
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)

        popularMovieAdapter = PopularMovieAdapter()
        popularMovieBottomAdapter = DefaultBottomLoadStateAdapter { popularMovieAdapter.retry() }

        binding.homeRv.apply {
            this.layoutManager = layoutManager
            adapter = popularMovieAdapter.withLoadStateFooter(popularMovieBottomAdapter)
        }

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == popularMovieAdapter.itemCount
                    && popularMovieBottomAdapter.itemCount > 0
                ) {
                    SPAN_COUNT
                } else {
                    1
                }
            }
        }

        listenLoadState()
    }

    private fun initSwipeRefresh() {
        binding.homeSrl.setOnRefreshListener {
            popularMovieAdapter.refresh()
        }
    }

    private fun listenLoadState() {
        popularMovieAdapter.addLoadStateListener {
            with(binding) {
                val state = it.refresh
                homeSrl.isRefreshing = state is LoadState.Loading
                if (state is LoadState.NotLoading) {
                    homeError.root.isVisible = popularMovieAdapter.itemCount == 0
                }
                if (state is LoadState.Error) {
                    homeError.root.isVisible = popularMovieAdapter.itemCount == 0
                    homeError.errorTryAgainButton.setOnClickListener {
                        homeSrl.isRefreshing = true
                        popularMovieAdapter.refresh()
                    }
                }
            }
        }
    }
}
