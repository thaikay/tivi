package com.thaikay.tivi.data.repository

import com.thaikay.tivi.data.local.ChannelDao
import com.thaikay.tivi.data.local.PlaylistDao
import com.thaikay.tivi.data.model.Channel
import com.thaikay.tivi.data.model.Playlist
import com.thaikay.tivi.data.parser.M3UParser
import com.thaikay.tivi.data.remote.M3UService
import kotlinx.coroutines.flow.Flow

class ChannelRepository(
    private val channelDao: ChannelDao,
    private val playlistDao: PlaylistDao,
    private val m3uService: M3UService,
    private val m3uParser: M3UParser
) {
    fun getAllPlaylists(): Flow<List<Playlist>> = playlistDao.getAllPlaylists()

    fun getChannelsByPlaylist(playlistId: Int): Flow<List<Channel>> =
        channelDao.getChannelsByPlaylist(playlistId)

    fun getFavoriteChannels(): Flow<List<Channel>> = channelDao.getFavoriteChannels()

    fun searchChannels(query: String): Flow<List<Channel>> =
        channelDao.searchChannels(query)

    suspend fun addPlaylist(url: String, name: String): Result<Playlist> = try {
        val content = m3uService.fetchM3U(url)
        val channels = m3uParser.parse(content, 0)

        val playlist = Playlist(
            name = name,
            url = url,
            channelCount = channels.size,
            lastUpdated = System.currentTimeMillis()
        )

        val playlistId = playlistDao.insert(playlist).toInt()
        val playlistChannels = channels.map { it.copy(playlistId = playlistId) }
        channelDao.insertAll(playlistChannels)

        Result.success(playlist.copy(id = playlistId))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun updateChannelFavorite(channel: Channel, isFavorite: Boolean) {
        channelDao.update(channel.copy(isFavorite = isFavorite))
    }

    suspend fun deletePlaylist(playlist: Playlist) {
        channelDao.deleteByPlaylist(playlist.id)
        playlistDao.delete(playlist)
    }
}
