package com.example.artbooktesting.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.artbooktesting.R
import com.example.artbooktesting.adapters.ArtRecyclerAdapter
import com.example.artbooktesting.databinding.FragmentArtsBinding
import com.example.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtFragment @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

    private var fragmentArt: FragmentArtsBinding? = null
    private val artViewModel: ArtViewModel by activityViewModels()
    private val swiperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedArt = artRecyclerAdapter.arts[layoutPosition]
                artViewModel.deleteArt(selectedArt)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtsBinding.bind(view)
        fragmentArt = binding

        binding.fabArt.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
        subscribeToObservers()
        initializeRecycler()
    }

    override fun onDestroyView() {
        fragmentArt = null
        super.onDestroyView()
    }

    private fun initializeRecycler() {
        fragmentArt!!.recyclerView.apply {
            adapter = artRecyclerAdapter
        }
        ItemTouchHelper(swiperCallback).attachToRecyclerView(fragmentArt!!.recyclerView)
    }


    private fun subscribeToObservers() {
        artViewModel.artList.observe(viewLifecycleOwner) {
            artRecyclerAdapter.submitList(it)
        }
    }
}