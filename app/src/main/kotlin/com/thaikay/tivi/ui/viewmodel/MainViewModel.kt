package com.thaikay.tivi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thaikay.tivi.data.local.ThaiTVDatabase
import com.thaikay.tivi.data.model.Channel
import com.thaikay.tivi.data.model.Playlist
import com.thaikay.tivi.data.parser.M3UParser
import com.thaikay.tivi.data.remote.M3UService
import com.thaikay.tivi.data.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ChannelRepository
) : ViewModel() {

    private val selectedPlaylistId = MutableStateFlow<Int?>(null)

    val playlists: Flow<List<Playlist>> = repository.getAllPlaylists()

    val channels: Flow<List<Channel>> = selectedPlaylistId.flatMapLatest { playlistId ->
        if (playlistId != null) {
            repository.getChannelsByPlaylist(playlistId)
        } else {
            repository.getFavoriteChannels()
        }
    }

    fun selectPlaylist(playlist: Playlist) {
        selectedPlaylistId.value = playlist.id
    }

    fun searchChannels(query: String): Flow<List<Channel>> {
        return repository.searchChannels(query)
    }

    fun addPlaylist(url: String) {
        viewModelScope.launch {
            val name = url.substringAfterLast("/").substringBeforeLast(".")
            repository.addPlaylist(url, name)
        }
    }

    fun toggleFavorite(channel: Channel) {
        viewModelScope.launch {
            repository.updateChannelFavorite(channel, !channel.isFavorite)
        }
    }

    fun deletePlaylist(playlist: Playlist) {
        viewModelScope.launch {
            repository.deletePlaylist(playlist)
        }
    }
}

class MainViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val database = ThaiTVDatabase.getInstance(application)
            val repository = ChannelRepository(
                database.channelDao(),
                database.playlistDao(),
                M3UService(),
                M3UParser()
            )
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
