package com.route.data.repo.address.di

import com.route.data.data_source.local.database.AddressLocalDataSourceImpl
import com.route.data.data_source.remote.address.AddressRemoteDataSourceImpl
import com.route.data.repo.address.AddressRepositoryImpl
import com.route.domain.repo.address.AddressLocalDataSource
import com.route.domain.repo.address.AddressRemoteDataSource
import com.route.domain.repo.address.AddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AddressModule {

    @Binds
    @Singleton
    abstract fun bindAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository

    @Binds
    @Singleton
    abstract fun bindAddressRemoteDataSource(impl: AddressRemoteDataSourceImpl): AddressRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAddressLocalDataSource(impl: AddressLocalDataSourceImpl): AddressLocalDataSource
}
