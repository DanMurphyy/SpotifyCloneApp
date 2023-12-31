package com.hfad.spotifycloneapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hfad.spotifycloneapp.R
import com.hfad.spotifycloneapp.adapters.SwipeSongAdapter
import com.hfad.spotifycloneapp.data.remote.MusicDatabase
import com.hfad.spotifycloneapp.exoplayer.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context
    ) = MusicServiceConnection(context)

    @Provides
    @Singleton
    fun provideGlideInstance(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Singleton
    @Provides
    fun provideMusicDatabase(): MusicDatabase {
        return MusicDatabase()
    }

    @Singleton
    @Provides
    fun provideSwipeSongAdapter() = SwipeSongAdapter()
}