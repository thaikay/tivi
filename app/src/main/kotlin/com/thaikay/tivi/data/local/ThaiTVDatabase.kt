package com.thaikay.tivi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thaikay.tivi.data.model.Channel
import com.thaikay.tivi.data.model.Playlist

@Database(
    entities = [Playlist::class, Channel::class],
    version = 1,
    exportSchema = false
)
abstract class ThaiTVDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun channelDao(): ChannelDao

    companion object {
        @Volatile
        private var INSTANCE: ThaiTVDatabase? = null

        fun getInstance(context: Context): ThaiTVDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ThaiTVDatabase::class.java,
                    "thaitv_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
