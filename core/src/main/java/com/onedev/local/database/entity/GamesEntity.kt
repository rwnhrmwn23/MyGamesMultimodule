package com.onedev.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_games")
data class GamesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gamesId: Int,
    val backgroundImage: String,
    val name: String,
    val rating: Double,
    val released: String,
    val shortScreenshots: String
)