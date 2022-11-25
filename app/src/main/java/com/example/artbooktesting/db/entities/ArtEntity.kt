package com.example.artbooktesting.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtEntity(
    var name:String,
    var artistName:String,
    var year:Int,
    var imageUrl:Int,
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
)
