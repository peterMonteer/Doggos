package com.pedro.doggos.feature_search.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.databinding.FragmentSearchBinding
import com.pedro.doggos.feature_detail.presentation.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var breedsAdapter: BreedsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchView: SearchView = binding.searchView
        val recyclerView = binding.searchResultsRecyclerView
        breedsAdapter = BreedsAdapter(::adapterItemOnClick)


        //TODO: Extract query text listener

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchBreeds(it)
                    }
                    return false
                }
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = breedsAdapter
        observeViewStates()
        return root
    }

    private fun observeViewStates() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is SearchViewModel.State.SearchBreedsSuccess -> {
                    breedsAdapter.setData(viewState.list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun adapterItemOnClick(breed: Breed) {
        startActivity(
            Intent(context, BreedDetailActivity::class.java).apply {
                putExtra(NAME, breed.name)
                putExtra(ORIGIN, breed.origin)
                putExtra(GROUP, breed.breedGroup)
                putExtra(TEMPERAMENT, breed.temperament)
            }
        )
    }
}