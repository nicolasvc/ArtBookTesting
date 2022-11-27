package com.example.artbooktesting.data.repository

import androidx.lifecycle.LiveData
import com.example.artbooktesting.api.response.ResponsePixBay
import com.example.artbooktesting.api.service.PixBayService
import com.example.artbooktesting.data.source.LocalArtDataSource
import com.example.artbooktesting.db.daos.ArtDao
import com.example.artbooktesting.db.entities.ArtEntity
import com.example.artbooktesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao:ArtDao,
    private val retrofitPixBay :PixBayService
) : LocalArtDataSource {
    override suspend fun insertArt(art: ArtEntity) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: ArtEntity) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<ArtEntity>>  = artDao.observeArts()

    override suspend fun searchImage(imageString: String): Resource<ResponsePixBay> {
     return  try {
         val response = retrofitPixBay.imageSearch(imageString)
         if(response.isSuccessful){
             response.body()?.let {
                 return@let Resource.success(it)
             }?: Resource.error("Error",null)
         }else{
             Resource.error("Error",null)
         }
     } catch (e:Exception){
         Resource.error("No Data!",null)
     }
    }
}