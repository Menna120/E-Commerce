package com.route.data.repo.auth.di

import com.route.data.data_source.remote.auth.AuthRemoteDataSourceImpl
import com.route.data.repo.auth.AuthRepositoryImpl
import com.route.domain.repo.auth.AuthRemoteDataSource
import com.route.domain.repo.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

}
