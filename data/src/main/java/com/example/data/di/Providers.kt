package com.example.data.di

import com.example.data.datasource.ProductRemoteDataSource
import com.example.data.repository.CartRepositoryImpl
import com.example.domain.CartRepository
import com.example.domain.datasource.ProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun provideGetProductDataSource(getProductsDataSource: ProductRemoteDataSource): ProductsDataSource

    @Binds
    abstract fun provideCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository
}