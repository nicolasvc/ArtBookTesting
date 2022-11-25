package com.example.artbooktesting.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artbooktesting.db.entities.ArtEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: ArtEntity)

    @Delete
    suspend fun deleteArt(art: ArtEntity)

    @Query("SELECT * FROM ArtEntity")
    fun observeArts(): Flow<List<ArtEntity>>

}