package dev.vladimir.search.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.core.data.common.observe
import dev.vladimir.search.R
import dev.vladimir.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var searchAdapter: MediaAdapter

//    override fun createBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        initRecycler()
        observeViewModel()
    }

    private fun initRecycler() {
        searchAdapter = MediaAdapter()

        binding.searchRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.searchMoviesState.observe(this) {
            searchAdapter.submitData(lifecycle, it)
        }
    }
}