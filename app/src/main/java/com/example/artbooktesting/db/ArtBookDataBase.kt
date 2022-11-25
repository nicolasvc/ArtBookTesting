package com.example.artbooktesting.db

import android.content.Context
import androidx.room.*
import com.example.artbooktesting.db.daos.ArtDao
import com.example.artbooktesting.db.entities.ArtEntity

@Database(
    entities = [ArtEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArtBookDataBase : RoomDatabase() {

    abstract fun artDao(): ArtDao

    companion object {

        @Synchronized
        fun getDataBase(context: Context): ArtBookDataBase = Room.databaseBuilder(
            context.applicationContext,
            ArtBookDataBase::class.java,
            "art_db"
        ).build()
    }
}