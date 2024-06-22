package com.example.corenetwork.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BindModule {
    @Provides
    @Singleton
    fun provideGsonBulder(): GsonBuilder{
        return GsonBuilder()
    }
}