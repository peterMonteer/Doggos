package com.pedro.doggos.feature_home.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.core.presentation.UIState
import com.pedro.doggos.databinding.FragmentHomeBinding
import com.pedro.doggos.feature_detail.presentation.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var adapter: BreedImageRxAdapter = BreedImageRxAdapter(::adapterItemOnClick)
    private val disposable = CompositeDisposable()

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
        progressBar = binding.progressBar.progressBar

        val gridLayoutManager = GridLayoutManager(context,1)

        layoutButton.setOnClickListener {
            gridLayoutManager.spanCount = if (gridLayoutManager.spanCount == 1) 2 else 1
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        viewModel.breedImageList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = !isListEmpty

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    val message = if (it.error is IOException) "A problem occurred please check your internet connection" else "An error occurred"
                    Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        orderButton.setOnClickListener {
            adapter.submitData(lifecycle, PagingData.empty())
            viewModel.getImages()
        }

        observeViewStates()
        return root
    }

    override fun onResume() {
        viewModel.getImages()
        super.onResume()
    }

    override fun onDestroyView() {
        disposable.dispose()
        _binding = null
        super.onDestroyView()
    }

    private fun observeViewStates() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is UIState.ErrorState -> {
                    Toast.makeText(
                        context,
                        viewState.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun adapterItemOnClick(breed: Breed) {
        startActivity(
            Intent(context, BreedDetailActivity::class.java).apply {
                putExtra(NAME, breed.name)
                putExtra(ORIGIN, breed.origin)
                putExtra(GROUP, breed.group)
                putExtra(TEMPERAMENT, breed.temperament)
            }
        )
    }
}