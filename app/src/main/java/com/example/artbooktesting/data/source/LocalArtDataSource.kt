package com.example.artbooktesting.data.source

import androidx.lifecycle.LiveData
import com.example.artbooktesting.api.response.ResponsePixBay
import com.example.artbooktesting.db.entities.ArtEntity
import com.example.artbooktesting.util.Resource

interface LocalArtDataSource {

    suspend fun insertArt(art: ArtEntity)

    suspend fun deleteArt(art: ArtEntity)

    fun getArt(): LiveData<List<ArtEntity>>

    suspend fun searchImage(imageString: String): Resource<ResponsePixBay>
}