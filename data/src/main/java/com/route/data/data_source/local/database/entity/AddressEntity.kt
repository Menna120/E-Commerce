package com.route.data.data_source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressEntity(
    val phone: String? = null,
    val city: String? = null,
    val name: String? = null,
    val details: String? = null,
    @PrimaryKey(autoGenerate = false) val id: String
)
