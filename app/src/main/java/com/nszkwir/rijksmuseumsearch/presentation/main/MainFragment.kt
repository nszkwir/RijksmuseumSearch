package com.nszkwir.rijksmuseumsearch.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nszkwir.rijksmuseumsearch.databinding.MainFragmentBinding
import com.nszkwir.rijksmuseumsearch.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

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
        // TODO
    }

    private fun setupView() {
        // TODO
    }
}