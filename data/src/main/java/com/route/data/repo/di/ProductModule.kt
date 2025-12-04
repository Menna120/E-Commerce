package com.route.data.repo.di

import com.route.data.data_source.remote.product.ProductRemoteDataSourceImpl
import com.route.data.repo.product.ProductsRepositoryImpl
import com.route.domain.repo.product.ProductRemoteDataSource
import com.route.domain.repo.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {
    @Binds
    @Singleton
    abstract fun provideProductRepository(impl: ProductsRepositoryImpl): ProductRepository
    @Binds
    @Singleton
    abstract fun provideProductDataSource(impl: ProductRemoteDataSourceImpl): ProductRemoteDataSource
}