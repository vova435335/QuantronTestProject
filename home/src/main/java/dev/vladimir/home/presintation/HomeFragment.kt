package dev.vladimir.home.presintation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.core.presentation.adapters.DefaultBottomLoadStateAdapter
import dev.vladimir.home.databinding.FragmentHomeBinding

private const val SPAN_COUNT = 3

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var popularMovieBottomAdapter: DefaultBottomLoadStateAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                when (it.refresh) {
                    is LoadState.Loading -> {
                        homeSrl.isRefreshing = true
                    }
                    is LoadState.Error -> {
                        homeSrl.isRefreshing = false
                        homeError.root.isVisible = popularMovieAdapter.itemCount == 0
                    }
                    is LoadState.NotLoading -> {
                        homeSrl.isRefreshing = false
                        homeError.root.isVisible = popularMovieAdapter.itemCount == 0
                    }
                }
            }
        }
    }
}
