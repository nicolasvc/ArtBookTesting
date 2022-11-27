package com.example.artbooktesting.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.artbooktesting.R
import com.example.artbooktesting.adapters.ImageRecyclerAdapter
import com.example.artbooktesting.databinding.FragmentImageApiBinding
import com.example.artbooktesting.util.Status
import com.example.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageApiFragment @Inject constructor(private val imageRecyclerAdapter: ImageRecyclerAdapter) :
    Fragment(R.layout.fragment_image_api), ImageRecyclerAdapter.Interaction {


    private val artViewModel: ArtViewModel by activityViewModels()
    private var fragmentBinding: FragmentImageApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentImageApiBinding.bind(view)
        fragmentBinding = binding
        initializeRecycler()
        subscribeToObservers()
        initializeSearchEditText()
    }

    private fun initializeSearchEditText() {
        var job:Job? = null
        fragmentBinding?.editTextFindImage?.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1500)
                it?.let {
                    if(it.toString().isNotEmpty()){
                        artViewModel.searchImage(it.toString())
                    }
                }
            }
        }
    }

    private fun subscribeToObservers() {
        artViewModel.imagesList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult ->
                        imageResult.previewURL
                    }
                    imageRecyclerAdapter.submitList(urls ?: listOf())
                    fragmentBinding?.progressBar?.isGone
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    fragmentBinding?.progressBar?.isGone
                }
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.isVisible
                }
            }
        }
    }

    private fun initializeRecycler() {
        fragmentBinding!!.imageRecyclerView.apply {
            adapter = imageRecyclerAdapter
        }
        imageRecyclerAdapter.setOnItemClickListener(this)
    }

    override fun onItemSelected(url: String) {
        findNavController().popBackStack()
        artViewModel.setSelectedImage(url)

    }
}