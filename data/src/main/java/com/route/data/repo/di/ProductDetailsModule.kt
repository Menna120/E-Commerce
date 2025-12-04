package com.route.data.repo.di

import com.route.data.data_source.remote.productDetails.ProductDetailsRemoteDataSourceImpl
import com.route.data.repo.productDetails.ProductDetailsRepositoryImpl
import com.route.domain.repo.productDetails.ProductDetailsRemoteDataSource
import com.route.domain.repo.productDetails.ProductDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDetailsModule {
    @Binds
    @Singleton
    abstract fun provideProductDetailsRepository(impl: ProductDetailsRepositoryImpl): ProductDetailsRepository
    @Binds
    @Singleton
    abstract fun provideProductDetailsDataSource(impl: ProductDetailsRemoteDataSourceImpl): ProductDetailsRemoteDataSource
}