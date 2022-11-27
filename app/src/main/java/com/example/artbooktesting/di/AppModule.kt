package com.example.artbooktesting.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artbooktesting.R
import com.example.artbooktesting.api.service.PixBayService
import com.example.artbooktesting.data.repository.ArtRepository
import com.example.artbooktesting.data.source.LocalArtDataSource
import com.example.artbooktesting.db.ArtBookDataBase
import com.example.artbooktesting.db.daos.ArtDao
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
    fun injectPixBayService() =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build().create(PixBayService::class.java)

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )

    @Singleton
    @Provides
    fun injectNormalRepo(dao:ArtDao, retrofit:PixBayService) = ArtRepository(dao,retrofit) as LocalArtDataSource


}