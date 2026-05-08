package com.thaikay.tivi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thaikay.tivi.R
import com.thaikay.tivi.databinding.ActivityMainBinding
import com.thaikay.tivi.ui.adapter.ChannelAdapter
import com.thaikay.tivi.ui.adapter.PlaylistAdapter
import com.thaikay.tivi.ui.viewmodel.MainViewModel
import com.thaikay.tivi.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(application)
    }

    private lateinit var playlistAdapter: PlaylistAdapter
    private lateinit var channelAdapter: ChannelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerViews()
        setupListeners()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setupRecyclerViews() {
        // Playlists RecyclerView
        playlistAdapter = PlaylistAdapter { playlist ->
            viewModel.selectPlaylist(playlist)
        }
        binding.playlistsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = playlistAdapter
        }

        // Channels RecyclerView
        channelAdapter = ChannelAdapter { channel ->
            startPlayerActivity(channel)
        }
        binding.channelsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = channelAdapter
        }
    }

    private fun setupListeners() {
        binding.fabAddPlaylist.setOnClickListener {
            showAddPlaylistDialog()
        }

        binding.fabSearch.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.playlists.collectLatest { playlists ->
                playlistAdapter.submitList(playlists)
            }
        }

        lifecycleScope.launch {
            viewModel.channels.collectLatest { channels ->
                channelAdapter.submitList(channels)
            }
        }
    }

    private fun showAddPlaylistDialog() {
        val editText = android.widget.EditText(this).apply {
            hint = "Enter M3U URL"
        }

        MaterialAlertDialogBuilder(this)
            .setTitle("Add Playlist")
            .setView(editText)
            .setPositiveButton("Add") { dialog, _ ->
                val url = editText.text.toString().trim()
                if (url.isNotEmpty()) {
                    lifecycleScope.launch {
                        viewModel.addPlaylist(url)
                    }
                } else {
                    Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSearchDialog() {
        val editText = android.widget.EditText(this).apply {
            hint = "Search channels..."
        }

        MaterialAlertDialogBuilder(this)
            .setTitle("Search")
            .setView(editText)
            .setPositiveButton("Search") { dialog, _ ->
                val query = editText.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchChannels(query)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun startPlayerActivity(channel: com.thaikay.tivi.data.model.Channel) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra("channel_url", channel.url)
            putExtra("channel_name", channel.name)
            putExtra("channel_logo", channel.logo)
        }
        startActivity(intent)
    }
}
