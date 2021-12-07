package com.nszkwir.rijksmuseumsearch.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nszkwir.rijksmuseumsearch.databinding.MainFragmentBinding
import com.nszkwir.rijksmuseumsearch.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var keywordSearchResultAdapter: KeywordSearchResultAdapter

    private val viewModel: MainViewModel by viewModels()
    override fun obtainViewModel() = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        defineObservables()
        setupView()
        return binding.root
    }

    private fun defineObservables() {
        viewModel.artObjects.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { artObjects ->
                keywordSearchResultAdapter.setData(artObjects)
            }
            viewModel.processViewEvent(MainViewEvent.LoadingView(false))
        }
        viewModel.searchError.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { errorMessage ->
                showErrorMessage(errorMessage)
            }
        }
    }

    private fun setupView() {
        keywordSearchResultAdapter = KeywordSearchResultAdapter()
        binding.keywordSearchResult.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = keywordSearchResultAdapter
        }

        binding.keywordSearch.apply {
            setOnErrorMessage { message ->
                showErrorMessage(message)
            }
            setOnSearchFunction { query ->
                if (query.isBlank()) {
                    showErrorMessage("You must add at least 1 keyword to proceed.")
                } else {
                    viewModel.processViewEvent(MainViewEvent.LoadingView(true))
                    viewModel.processViewEvent(MainViewEvent.KeywordSearch(query))
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        viewModel.processViewEvent(MainViewEvent.LoadingView(false))
        viewModel.processViewEvent(MainViewEvent.ErrorMessage(message))
    }
}
