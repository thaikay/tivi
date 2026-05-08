package com.thaikay.tivi.data.parser

import com.thaikay.tivi.data.model.Channel

class M3UParser {
    fun parse(content: String, playlistId: Int): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = content.split("\n")

        var currentChannel: Channel.Builder? = null

        for (line in lines) {
            val trimmedLine = line.trim()

            when {
                trimmedLine.startsWith("#EXTINF:") -> {
                    currentChannel = Channel.Builder(playlistId)
                    parseExtinf(trimmedLine, currentChannel)
                }
                trimmedLine.startsWith("#") -> continue
                trimmedLine.isEmpty() -> continue
                currentChannel != null -> {
                    currentChannel.url = trimmedLine
                    channels.add(currentChannel.build())
                    currentChannel = null
                }
            }
        }

        return channels
    }

    private fun parseExtinf(line: String, builder: Channel.Builder) {
        val content = line.substringAfter("#EXTINF:")
        val parts = content.split(",", limit = 2)

        if (parts.size > 1) {
            builder.name = parts[1].trim()
        }

        // Extract tvg-id
        val tvgIdMatch = Regex("tvg-id=\"([^\"]*)\"").find(content)
        if (tvgIdMatch != null) {
            builder.tvgId = tvgIdMatch.groupValues[1]
        }

        // Extract tvg-name
        val tvgNameMatch = Regex("tvg-name=\"([^\"]*)\"").find(content)
        if (tvgNameMatch != null) {
            builder.tvgName = tvgNameMatch.groupValues[1]
        }

        // Extract tvg-logo
        val logoMatch = Regex("tvg-logo=\"([^\"]*)\"").find(content)
        if (logoMatch != null) {
            builder.logo = logoMatch.groupValues[1]
        }

        // Extract group-title
        val groupMatch = Regex("group-title=\"([^\"]*)\"").find(content)
        if (groupMatch != null) {
            builder.group = groupMatch.groupValues[1]
        }
    }

    data class Builder(
        val playlistId: Int,
        var name: String = "",
        var url: String = "",
        var logo: String = "",
        var group: String = "",
        var tvgId: String = "",
        var tvgName: String = ""
    ) {
        fun build() = Channel(
            playlistId = playlistId,
            name = name,
            url = url,
            logo = logo,
            group = group,
            tvgId = tvgId,
            tvgName = tvgName
        )
    }
}
