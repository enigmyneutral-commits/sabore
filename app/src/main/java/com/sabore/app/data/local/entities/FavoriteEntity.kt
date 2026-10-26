package com.sabore.app.data.local.entities

import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["userId", "recipeId"])
data class FavoriteEntity(
    val userId: String,
    val recipeId: String,
    val createdAt: Long = System.currentTimeMillis()
)
