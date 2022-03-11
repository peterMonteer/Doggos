package com.pedro.doggos.feature_home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.databinding.FragmentHomeBinding
import com.pedro.doggos.feature_home.domain.model.BreedImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView

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

        recyclerView= binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)


        observeViewStates()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewStates() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is HomeViewModel.State.GetImagesSuccess -> {
                    showResults(viewState.list)
                }
            }
        }
    }

    private fun showResults(list: List<BreedImage>) {
        recyclerView.adapter = BreedImageAdapter(list)
    }
}