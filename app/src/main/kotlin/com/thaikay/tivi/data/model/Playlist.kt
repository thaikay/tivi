package com.thaikay.tivi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String,
    val channelCount: Int = 0,
    val lastUpdated: Long = 0L,
    val isActive: Boolean = true
)
