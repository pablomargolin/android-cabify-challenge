package com.example.data.di

import com.example.data.datasource.GetProductDataSourceImpl
import com.example.domain.datasource.GetProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun provideGetProductDataSource(getProductsDataSource: GetProductDataSourceImpl): GetProductsDataSource
}