package com.example.data.di

import com.example.data.datasource.GetProductDataSourceImpl
import com.example.data.repository.CartRepositoryImp
import com.example.domain.CartRepository
import com.example.domain.datasource.GetProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun provideGetProductDataSource(getProductsDataSource: GetProductDataSourceImpl): GetProductsDataSource

    @Binds
    @Singleton
    abstract fun provideCartRepository(cartRepositoryImp: CartRepositoryImp): CartRepository
}