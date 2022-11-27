package com.example.artbooktesting.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.FragmentArtDetailsBinding
import com.example.artbooktesting.util.Status
import com.example.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtDetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {

    private var fragmentArtDetailBinding: FragmentArtDetailsBinding? = null
    private val artViewModel: ArtViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentArtDetailBinding = binding

        binding.selectImageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.buttonSave.setOnClickListener {
            artViewModel.makeArt(
                binding.editTextPersonName.text.toString(),
                binding.editTextArtist.text.toString(),
                binding.editTextYear.text.toString()
            )
        }
        subscribeToObserver()
    }

    private fun subscribeToObserver() {
        artViewModel.insertArtMessage.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> {
                    createToast(it.message!!)
                }
                Status.SUCCESS -> {
                    createToast("Success")
                    findNavController().popBackStack()
                    artViewModel.resetInsertArtMsg()
                }
                Status.LOADING -> {}
            }
        }
        artViewModel.selectedImageUrl.observe(viewLifecycleOwner) { url ->
            fragmentArtDetailBinding?.let {
                glide.load(url).into(it.selectImageView)
            }
        }
    }

    private fun createToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        fragmentArtDetailBinding = null
        super.onDestroyView()
    }
}