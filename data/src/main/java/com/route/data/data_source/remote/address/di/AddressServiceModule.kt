package com.route.data.data_source.remote.address.di

import com.route.data.data_source.remote.address.api.AddressService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressServiceModule {

    @Provides
    @Singleton
    fun provideAddressService(retrofit: Retrofit): AddressService =
        retrofit.create(AddressService::class.java)
}
