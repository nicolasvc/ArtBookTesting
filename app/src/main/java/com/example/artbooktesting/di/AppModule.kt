package com.example.artbooktesting.di

import android.content.Context
import androidx.room.Room
import com.example.artbooktesting.api.service.PixBayService
import com.example.artbooktesting.db.ArtBookDataBase
import com.example.artbooktesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ArtBookDataBase::class.java, "ArtBookDB").build()


    @Singleton
    @Provides
    fun injectDao(database: ArtBookDataBase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofit() =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build().create(PixBayService::class.java)


}