package com.route.data.data_source.local.database.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.data.data_source.local.database.entity.AddressEntity
import com.route.data.data_source.local.database.dao.AddressDao
import com.route.data.localStorage.converter.Converters
import com.route.data.localStorage.dao.ProductsDao
import com.route.data.localStorage.product.ProductCacheEntity
import com.route.data.localStorage.entity.ProductDetailsCacheEntity
import com.route.data.localStorage.dao.ProductDetailsDao


@Database(entities = [AddressEntity::class,
    ProductCacheEntity::class,
    ProductDetailsCacheEntity::class], version = 3, exportSchema = false)

@TypeConverters(Converters::class)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    abstract fun productsDao(): ProductsDao

    abstract fun productDetailsDao(): ProductDetailsDao
}
