package com.example.artbooktesting.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artbooktesting.db.entities.ArtEntity

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: ArtEntity)

    @Delete
    suspend fun deleteArt(art: ArtEntity)

    @Query("SELECT * FROM ArtEntity")
    fun observeArts(): LiveData<List<ArtEntity>>

}