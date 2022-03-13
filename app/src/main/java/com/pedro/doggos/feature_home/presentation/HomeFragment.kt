package com.pedro.doggos.feature_home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.databinding.FragmentHomeBinding
import com.pedro.doggos.feature_detail.presentation.*
import com.pedro.doggos.feature_home.domain.model.BreedImage
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private val disposable = CompositeDisposable()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val layoutButton = binding.layoutButton
        val orderButton = binding.orderButton
        recyclerView = binding.recyclerView

        val gridLayoutManager = GridLayoutManager(context,1)

        layoutButton.setOnClickListener {
            gridLayoutManager.spanCount = if (gridLayoutManager.spanCount == 1) 2 else 1
        }

        orderButton.setOnClickListener {
            viewModel.getImages()
        }

        //TODO: INJECT THIS
        val rxAdapter = BreedImageRxAdapter(::adapterItemOnClick)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = rxAdapter

        viewModel.breedImageList.observe(viewLifecycleOwner) {
                rxAdapter.submitData(lifecycle, it)
            }

        observeViewStates()
        return root
    }

    override fun onDestroyView() {
        disposable.dispose()
        _binding = null
        super.onDestroyView()
    }

    private fun observeViewStates() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is HomeViewModel.State.GetImagesSuccess -> {
                }
            }
        }
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