package com.example.rebottle.ui

import HistoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rebottle.data.RecyclingHistory
import com.example.rebottle.databinding.FragmentHistoryBinding
import com.example.rebottle.model.HistoryItem

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadHistoryData()
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadHistoryData() {
        val sampleData = listOf(
            HistoryItem(
                id = "1",  // Ajoutez un ID unique
                type = "Bouteille PET",
                price = 0.10,
                date = "12/05/2023"
            ),
            HistoryItem(
                id = "2",
                type = "Canette aluminium",
                price = 0.05,
                date = "10/05/2023"
            )
        )
        historyAdapter.submitList(sampleData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}