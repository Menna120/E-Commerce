package com.route.data.repo.di

import com.route.data.data_source.local.data_store.TokenPrefsImpl
import com.route.data.data_source.local.data_store.UserPrefsImpl
import com.route.domain.repo.local_storage.TokenPrefs
import com.route.domain.repo.local_storage.UserPrefs
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefsModule {

    @Binds
    @Singleton
    abstract fun bindsTokenPrefs(impl: TokenPrefsImpl): TokenPrefs

    @Binds
    @Singleton
    abstract fun bindsUserPrefs(impl: UserPrefsImpl): UserPrefs
}
