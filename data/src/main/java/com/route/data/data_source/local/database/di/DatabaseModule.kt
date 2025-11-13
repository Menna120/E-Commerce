package com.route.data.data_source.local.database.di

import android.content.Context
import androidx.room.Room
import com.route.data.data_source.local.database.dao.AddressDao
import com.route.data.data_source.local.database.db.ECommerceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideECommerceDatabase(
        @ApplicationContext context: Context
    ): ECommerceDatabase = Room.databaseBuilder(
        context,
        ECommerceDatabase::class.java,
        "ecommerce-database"
    ).fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideAddressDao(database: ECommerceDatabase): AddressDao = database.addressDao()
}
