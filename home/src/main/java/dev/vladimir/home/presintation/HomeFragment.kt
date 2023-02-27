package dev.vladimir.home.presintation

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.adapters.DefaultBottomLoadStateAdapter
import dev.vladimir.home.R
import dev.vladimir.home.databinding.FragmentHomeBinding
import dev.vladimir.home.domain.model.MediaType

private const val SPAN_COUNT = 3

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var popularMediaAdapter: PopularMediaAdapter
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
            popularMediaAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecycler() {
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)

        popularMediaAdapter = PopularMediaAdapter( openDetails = {
            mediaId, mediaType ->
            navigateToMediaDetails(mediaId, mediaType) }
        )
        popularMovieBottomAdapter = DefaultBottomLoadStateAdapter { popularMediaAdapter.retry() }

        binding.homeRv.apply {
            this.layoutManager = layoutManager
            adapter = popularMediaAdapter.withLoadStateFooter(popularMovieBottomAdapter)
        }

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == popularMediaAdapter.itemCount
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
            popularMediaAdapter.refresh()
        }
    }

    private fun listenLoadState() {
        popularMediaAdapter.addLoadStateListener {
            with(binding) {
                val state = it.refresh
                homeSrl.isRefreshing = state is LoadState.Loading
                if (state is LoadState.NotLoading) {
                    homeError.root.isVisible = popularMediaAdapter.itemCount == 0
                }
                if (state is LoadState.Error) {
                    homeError.root.isVisible = popularMediaAdapter.itemCount == 0
                    homeError.errorTryAgainButton.setOnClickListener {
                        homeSrl.isRefreshing = true
                        popularMediaAdapter.refresh()
                    }
                }
            }
        }
    }

    private fun navigateToMediaDetails(mediaId: String, mediaType: MediaType) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(getString(dev.vladimir.core.R.string.navigate_to_media_details)
                .replace("{media_id}", mediaId)
                .replace("{media_type}", mediaType.id.toString())
                .toUri())
            .build()
        findNavController().navigate(request)
    }
}
