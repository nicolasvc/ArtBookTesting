package com.example.artbooktesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbooktesting.api.response.ResponsePixBay
import com.example.artbooktesting.data.source.LocalArtDataSource
import com.example.artbooktesting.db.entities.ArtEntity
import com.example.artbooktesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository: LocalArtDataSource
) : ViewModel() {


    //Art fragment
    val artList = repository.getArt()

    // Image Api Fragment
    private val images = MutableLiveData<Resource<ResponsePixBay>>()
    val imagesList: LiveData<Resource<ResponsePixBay>> get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String> get() = selectedImage

    // Art Detail Fragment
    private var insertArtMsg = MutableLiveData<Resource<ArtEntity>>()
    val insertArtMessage: LiveData<Resource<ArtEntity>> get() = insertArtMsg


    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<ArtEntity>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: ArtEntity) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    private fun insertArt(art: ArtEntity) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArtMsg.postValue(Resource.error("Year should be number", null))
            return
        }

        val art = ArtEntity(name, artistName, yearInt, selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }


}