package dev.vladimir.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.core.presentation.BaseFragment
import dev.vladimir.search.data.paging.MediaType
import dev.vladimir.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var searchAdapter: MediaAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initSearchView()
        observeViewModel()
    }

    private fun initRecycler() {
        searchAdapter = MediaAdapter(openDetails = { mediaId, mediaType ->
            navigateToMediaDetails(mediaId, mediaType)
        })

        binding.searchRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }
    }

    private fun initSearchView() {
        binding.searchSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchMedia(newText)
                return false
            }
        })
    }

    private fun observeViewModel() {
        viewModel.searchMoviesState.observe(this) {
            searchAdapter.submitData(lifecycle, it)
        }
    }

    private fun navigateToMediaDetails(mediaId: String, mediaType: MediaType) {
//        val uri = getString(dev.vladimir.core.R.string.navigate_to_media_details)
        val request = NavDeepLinkRequest.Builder
            .fromUri(getString(dev.vladimir.core.R.string.navigate_to_media_details)
                .replace("{media_id}", mediaId)
                .replace("{media_type}", mediaType.id.toString())
                .toUri())
            .build()
        findNavController().navigate(request)
    }
}
