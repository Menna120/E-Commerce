package com.route.data.data_source.local.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.data.data_source.local.database.entity.AddressEntity
import com.route.data.data_source.local.database.dao.AddressDao

@Database(entities = [AddressEntity::class], version = 1, exportSchema = false)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}
