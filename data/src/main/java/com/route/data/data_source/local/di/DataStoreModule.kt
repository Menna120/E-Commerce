package com.route.data.data_source.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    const val PREFS_NAME = "e-commerce prefs"
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_NAME)

    @Provides
    @Singleton
    fun provideECommerceDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.dataStore
}
